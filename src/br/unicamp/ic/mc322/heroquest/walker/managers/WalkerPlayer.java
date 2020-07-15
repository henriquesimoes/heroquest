package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

import java.util.ArrayList;

public class WalkerPlayer extends WalkerManager {
    private PlayerInterface ioInterface;

    public void updateScreen() {
        ioInterface.showMessage(getStatus());
        ioInterface.showMap(walker.getPosition());
    }

    @Override
    public void playTurn() {
        boolean successfulMove = false;
        boolean successfulSkillUsage = false;

        while (true) {
            updateScreen();
            ArrayList<String> options = new ArrayList<>();
            options.add("Use items");
            if (!successfulSkillUsage)
                options.add("Use skill");
            if (!successfulMove)
                options.add("Execute a movement");

            ioInterface.showMessage("Choose an action:");
            int choice = ioInterface.showOptionsAndGetAnswer(options);

            if (choice == 0)
                return;
            if (choice == 1) {
                useItems();
            } else if (choice == 2 && !successfulSkillUsage) {
                successfulSkillUsage = useSkill();
            } else {
                successfulMove = makeMove();
            }
        }
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

        for (Skill skill: skills)
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

    @Override
    public void showMessage(String message) {
        ioInterface.showMessage(message);
    }
}
