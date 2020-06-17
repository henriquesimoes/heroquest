package br.unicamp.ic.mc322.heroquest.walker.manager.player;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class WalkerPlayer extends WalkerManager {
    private PlayerInterface ioInterface;

    public WalkerPlayer(Map map) {
        super(map);
        ioInterface = new PlayerInterface();
    }

    public void updateScreen(){
        ioInterface.showMessage(walker.getStatus());
        ioInterface.showMap(map, walker);
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
    protected int chooseItem(ArrayList<CollectableItem> items){
        ArrayList<String> nameList = new ArrayList<>();

        for (CollectableItem item : items)
            nameList.add(item.getItemName());

        ioInterface.showMessage("Choose an item to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);
        return choice;
    }

    @Override
    protected int chooseMove(ArrayList<Coordinate> possibleMoves) {
        ArrayList<String> movesList = new ArrayList<>();

        for (Coordinate coordinate : possibleMoves)
            movesList.add(coordinate.toString());

        ioInterface.showMessage("Choose a destination position:");
        int choice = ioInterface.showOptionsAndGetAnswer(movesList);

        return choice;
    }

    protected int chooseSkill(ArrayList<Skill> skills){
        ArrayList<String> nameList = new ArrayList<>();

        for (Skill skill: skills)
            nameList.add(skill.getSkillName());

        ioInterface.showMessage("Choose a skill to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        return choice;
    }

    protected int chooseTargetSkill(ArrayList<MapObject> targets){
        ArrayList<String> targetList = new ArrayList<>();

        for (MapObject target : targets)
            targetList.add(target.getRepresentationOnMenu());

        ioInterface.showMessage("Choose a target:");
        int choice = ioInterface.showOptionsAndGetAnswer(targetList);

        return choice;
    }

    @Override
    public void showMessage(String message) {
        ioInterface.showMessage(message);
    }
}
