package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
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
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.hero.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Elf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.WizardSkeleton;

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
        ioInterface.showMessage(getStatus());
        ioInterface.showMap(walker.getPosition());
    }

    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    @Override
    public void playTurn() {
        ArrayList<Action> options = new ArrayList<Action>(Arrays.asList(
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
        if (!secretDoor.isDiscovered())
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

    public MapObject chooseTarget(MapObject[] targets) {
        String[] targetList = new String[targets.length];

        for (int i = 0; i < targets.length; i++)
            targetList[i] = targets[i].getRepresentationOnMenu();

        ioInterface.showMessage("Choose a target:");
        int choice = ioInterface.showOptionsAndGetAnswer(targetList, true) - 1;

        return choice == -1 ? null : targets[choice];
    }

    @Override
    protected void setMap(Map map) {
        changeMap(map);
        ioInterface.setMap(map);
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
