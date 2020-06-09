package br.unicamp.ic.mc322.heroquest.walker.manager.player;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class WalkerPlayer extends WalkerManager {
    private PlayerInterface ioInterface;

    public WalkerPlayer(Walker walker) {
        super(walker);
        ioInterface = new PlayerInterface();
    }

    @Override
    public void playTurn() {
        boolean successfulMove = false;
        boolean successfulSkillUsage = false;

        while (true) {
            ArrayList<String> options = new ArrayList<>();
            options.add("Use Items");
            if (!successfulSkillUsage)
                options.add("Use Skill");
            if (!successfulMove)
                options.add("Execute Movement");

            ioInterface.showMessage("Choose an action:");
            int choice = ioInterface.showOptionsAndGetAnswer(options);

            if (choice == 0)
                return;
            if (choice == 1) {
                useItems();
            } else if (choice == 2 && !successfulSkillUsage) {
                successfulSkillUsage = useSkill();
            } else {
                successfulMove = move();
            }
        }
    }

    private void useItems() {
        ArrayList<CollectableItem> items = walker.getItems();
        ArrayList<String> nameList = new ArrayList<>();

        for (CollectableItem item : items)
            nameList.add(item.getItemName());

        ioInterface.showMessage("Choose an item to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        if (choice != 0) {
            CollectableItem choosedItem = items.get(choice - 1);
            choosedItem.useItem(walker);
        }
    }

    private boolean move() {
        int limitPositionInMove = walker.getPositionLimitInMovement();
        ArrayList<Coordinate> possibleMoves = visibleMap.getCloseWalkablePositions(limitPositionInMove);
        ArrayList<String> movesList = new ArrayList<>();

        for (Coordinate coordinate : possibleMoves)
            movesList.add(coordinate.toString());

        ioInterface.showMessage("Choose a position of destination");
        int choice = ioInterface.showOptionsAndGetAnswer(movesList);

        visibleMap.moveWalker(possibleMoves.get(choice - 1));
        return true;
    }

    private boolean useSkill() {
        ArrayList<Skill> skills = walker.getSkills();
        ArrayList<String> nameList = new ArrayList<>();

        for (Skill skill: skills)
            nameList.add(skill.getSkillName());

        ioInterface.showMessage("Choose a skill to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        if (choice != 0) {
            Skill chosenSkill = skills.get(choice - 1);
            ArrayList<MapObject> targets = chosenSkill.getTargets(this);
            ArrayList<String> targetList = new ArrayList<>();

            for (MapObject target : targets)
                targetList.add(target.getRepresentationOnMenu());

            ioInterface.showMessage("Choose a target:");
            choice = ioInterface.showOptionsAndGetAnswer(targetList);

            if (choice != 0) {
                MapObject target = targets.get(choice - 1);
                chosenSkill.useSkill(walker, target);
                return true;
            } else
                return false;
        }
        else
            return false;
    }
}
