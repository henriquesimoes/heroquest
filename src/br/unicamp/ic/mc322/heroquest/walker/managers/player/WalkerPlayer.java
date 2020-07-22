package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.HiddenObject;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Elf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WalkerPlayer extends WalkerManager implements ConcreteMapObjectVisitor {
    private IOInterface ioInterface;
    private Set<MapObject> objectsAdjacent;
    private Set<HiddenObject> hiddenObjectsDetected;

    public WalkerPlayer(IOInterface ioInterface) {
        this.ioInterface = ioInterface;
        objectsAdjacent = new HashSet<>();
        hiddenObjectsDetected = new HashSet<>();
    }

    void updateScreen() {
        ioInterface.showMap(walker.getPosition());
        ioInterface.showMessage(getStatus());
    }

    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    @Override
    public void playTurn() {
        ArrayList<Action> options = new ArrayList<>(Arrays.asList(
                new MovePlayerAction(this),
                new UseItemPlayerAction(this),
                new UseSkillPlayerAction(this),
                new InteractPlayerAction(this),
                new SearchPlayerAction(this),
                new SeeStatusPlayerAction(this)
        ));

        while (true) {
            updateScreen();
            String[] messages = new String[options.size()];
            for (int i = 0; i < options.size(); i++)
                messages[i] = (options.get(i).getDescription());

            ioInterface.showMessage("Choose an action:");
            int choice = ioInterface.showOptionsAndGetAnswer(messages, true) - 1;

            if (choice == -1)
                return;

            updateScreen();

            Action chosenAction = options.get(choice);
            if (chosenAction.execute())
                options.remove(chosenAction);
        }
    }

    @Override
    public void showMessage(String message) {
        ioInterface.showMessage(message);
    }

    @Override
    public void visit(Barbarian barbarian) {

    }

    @Override
    public void visit(Dwarf dwarf) {

    }

    @Override
    public void visit(Elf elf) {

    }

    @Override
    public void visit(Wizard wizard) {

    }

    @Override
    public void visit(WizardSkeleton wizardSkeleton) {

    }

    @Override
    public void visit(CommonSkeleton commonSkeleton) {

    }

    @Override
    public void visit(Goblin goblin) {

    }

    @Override
    public void visit(Floor floor) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(Door door) {
        objectsAdjacent.add(door);
    }

    @Override
    public void visit(SecretDoor secretDoor) {
        if (secretDoor.isDiscovered())
            objectsAdjacent.add(secretDoor);
        else
            hiddenObjectsDetected.add(secretDoor);
    }

    @Override
    public void visit(Chest chest) {
        objectsAdjacent.add(chest);
    }

    @Override
    public void visit(Trap trap) {
        if (!trap.isDiscovered())
            hiddenObjectsDetected.add(trap);
    }

    @Override
    protected void setMap(Map map) {
        changeMap(map);
        ioInterface.setMap(map);
    }

    MapObject chooseTargetByCoordinate(MapObject[] targets) {
        Coordinate[] coordinates = new Coordinate[targets.length];
        for (int i = 0; i < coordinates.length; i++)
            coordinates[i] = targets[i].getPosition();

        Coordinate chosenCoordinate = ioInterface.getCoordinate(coordinates);
        MapObject chosenObject = null;

        for (int i = 0; i < targets.length; i++) {
            if (chosenCoordinate.equals(targets[i].getPosition())) {
                chosenObject = targets[i];
                break;
            }
        }

        return chosenObject;
    }

    MapObject chooseTarget(MapObject[] targets) {
        String[] targetList = new String[targets.length];

        for (int i = 0; i < targets.length; i++)
            targetList[i] = targets[i].getRepresentationOnMenu();

        ioInterface.showMessage("Choose a target:");
        int choice = ioInterface.showOptionsAndGetAnswer(targetList, true) - 1;

        return choice == -1 ? null : targets[choice];
    }

    Describable chooseDescribable(Describable[] describable, String message) {
        String[] nameList = new String[describable.length];

        for (int i = 0; i < describable.length; i++)
            nameList[i] = describable[i].getName();

        updateScreen();
        ioInterface.showMessage(message);
        int choice = ioInterface.showOptionsAndGetAnswer(nameList, true) - 1;

        return choice == -1 ? null : describable[choice];
    }

    Set<MapObject> getObjectsAdjacent() {
        return objectsAdjacent;
    }

    Set<HiddenObject> getHiddenObjectsDetected() {
        return hiddenObjectsDetected;
    }

    IOInterface getIOInterface() {
        return ioInterface;
    }
}
