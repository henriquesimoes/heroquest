package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
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
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.WizardSkeleton;

import java.util.ArrayList;
import java.util.Arrays;

public class WalkerPlayer extends WalkerManager implements ConcreteMapObjectVisitor {
    private PlayerInterface ioInterface;
    ArrayList<MapObject> objectsAdjacent;

    public void updateScreen() {
        ioInterface.showMessage(getStatus());
        ioInterface.showMap(walker.getPosition());
    }

    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    @Override
    public void playTurn() {
        ArrayList<Actions> options = new ArrayList<>(Arrays.asList(Actions.values()));
        boolean removeOption;

        while (true) {
            updateScreen();
            ArrayList<String> messages = new ArrayList<>();
            for (Actions option : options)
                messages.add(option.toString());

            ioInterface.showMessage("Choose an action:");
            int choice = ioInterface.showOptionsAndGetAnswer(messages);

            if (choice == 0)
                return;

            switch (options.get(choice - 1)){
                case MOVE:
                    removeOption = makeMove();
                    break;
                case USEITEM:
                    removeOption = useItems();
                    break;
                case USESKILL:
                    removeOption = useSkill();
                    break;
                case INTERACT:
                    removeOption = interactWithObjects();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (removeOption)
                options.remove(choice - 1);
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

    }

    @Override
    public void visit(Chest chest) {
        objectsAdjacent.add(chest);
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

    protected MapObject chooseTarget(ArrayList<MapObject> targets) {
        ArrayList<String> targetList = new ArrayList<>();

        for (MapObject target : targets)
            targetList.add(target.getRepresentationOnMenu());

        ioInterface.showMessage("Choose a target:");
        int choice = ioInterface.showOptionsAndGetAnswer(targetList);

        return choice == 0 ? null : targets.get(choice - 1);
    }

    @Override
    protected boolean makeMove() {
        int limitPositionInMove = walker.getPositionLimitInMovement();

        for (int i = limitPositionInMove; i > 0;){
            ioInterface.showMessage(String.format("Remaining movements: %d", i));
            Direction direction = ioInterface.getMoveDirection();

            if (direction == null)
                break;

            Coordinate chosenMove = walker.getPosition().shift(direction);
            Region region = regionSelector.getCardinalRegion(true);
            ArrayList<Coordinate> possibleMoves = region.toArrayList();

            if (possibleMoves.contains(chosenMove)){
                moveWalker(chosenMove);
                i--;
            }else
                ioInterface.showMessage("Invalid movement");
            updateScreen();
        }
        return true;
    }

    @Override
    protected void setMap(Map map){
        changeMap(map);
        ioInterface = new PlayerInterface(map);
    }

    private boolean interactWithObjects() {
        objectsAdjacent = new ArrayList<>();

        Region region = regionSelector.getAdjacentRegion(false);
        accept(this, region);

        MapObject chosenTarget = chooseTarget(objectsAdjacent);

        if (chosenTarget != null)
            chosenTarget.interact(walker);

        return false;
    }
}