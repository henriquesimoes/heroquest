package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
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
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.hero.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Elf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.*;
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.WizardSkeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WalkerPlayer extends WalkerManager implements ConcreteMapObjectVisitor {
    private PlayerInterface ioInterface;
    private Set<MapObject> objectsAdjacent;
    private Set<HiddenObject> hiddenObjectsDetected;

    public WalkerPlayer() {
        objectsAdjacent = new HashSet<>();
        hiddenObjectsDetected = new HashSet<>();
    }

    public void updateScreen() {
        ioInterface.showMessage(getStatus());
        ioInterface.showMap(walker.getPosition());
    }

    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    @Override
    public void playTurn() {
        ArrayList<Action> options = new ArrayList<>(Arrays.asList(
                new MoveAction(this),
                new UseItemAction(this),
                new UseSkillAction(this),
                new InteractAction(this),
                new SearchAction(this)
        ));

        while (true) {
            updateScreen();
            ArrayList<String> messages = new ArrayList<>();
            for (Action option : options)
                messages.add(option.getDescription());

            ioInterface.showMessage("Choose an action:");
            int choice = ioInterface.showOptionsAndGetAnswer(messages);

            if (choice == 0)
                return;

            Action chosenAction = options.get(choice - 1);

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

    @Override
    protected CollectableItem chooseItem(ArrayList<CollectableItem> items) {
        ArrayList<String> nameList = new ArrayList<>();

        for (CollectableItem item : items)
            nameList.add(item.getItemName());

        ioInterface.showMessage("Choose an item to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        return choice == 0 ? null : items.get(choice - 1);
    }

    protected Skill chooseSkill(ArrayList<Skill> skills) {
        ArrayList<String> nameList = new ArrayList<>();

        for (Skill skill : skills)
            nameList.add(skill.getSkillName());

        ioInterface.showMessage("Choose a skill to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        return choice == 0 ? null : skills.get(choice - 1);
    }

    public MapObject chooseTarget(ArrayList<MapObject> targets) {
        ArrayList<String> targetList = new ArrayList<>();

        for (MapObject target : targets)
            targetList.add(target.getRepresentationOnMenu());

        ioInterface.showMessage("Choose a target:");
        int choice = ioInterface.showOptionsAndGetAnswer(targetList);

        return choice == 0 ? null : targets.get(choice - 1);
    }

    @Override
    protected void setMap(Map map) {
        changeMap(map);
        ioInterface = new PlayerInterface(map);
    }

    public Set<MapObject> getObjectsAdjacent() {
        return objectsAdjacent;
    }

    public Set<HiddenObject> getHiddenObjectsDetected() {
        return hiddenObjectsDetected;
    }

    public PlayerInterface getPlayerInterface() {
        return ioInterface;
    }
}