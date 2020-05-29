package br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerPlayer;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.skills.Skill;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.visibleMap.VisibleMap;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.WalkerManager;

import java.util.ArrayList;

public class WalkerPlayer extends WalkerManager {
    private PlayerInterface ioInterface;
    private VisibleMap visibleMap;
    WalkerPlayer(){
        ioInterface = new PlayerInterface();
    }

    @Override
    public void playTurn(){
        boolean sucessMove = false;
        boolean sucessUseSkill = false;

        while(true) {
            ArrayList<String> options = new ArrayList<>();
            options.add("Use Items");
            if (!sucessMove)
                options.add("Execute Movement");
            if (!sucessUseSkill)
                options.add("Execute Skill");

            int choice = ioInterface.showOptionsAndGetAnswer(options);

            if (choice == 0)
                return;
            if (choice == 1){
                useItems();
            }else if(choice == 2 && !sucessMove){
                sucessMove = move();
            }else{
                sucessUseSkill = useSkill();
            }
        }
    }

    private void useItems(){
        ArrayList<CollectableItem> items = walker.getItems();
        ArrayList<String> nameList = new ArrayList<>();

        for (CollectableItem item : items){
            nameList.add(item.getName());
        }

        int choice = ioInterface.showOptionsAndGetAnswer(movesList);

        if (choice != 0){
            CollectableItem choosedItem = items.get(choice - 1);
            choosedItem.use(walker);
        }

    }

    private boolean move(){
        int limitPositionInMove = walker.getLimitPositionInMovement();
        ArrayList<Coordinate> possibleMoves = visibleMap.getPositionInDistance(limitPositionInMove);
        ArrayList<String> movesList = new ArrayList<>();

        for (Coordinate coordinate : possibleMoves){
            movesList.add(coordinate.toString());
        }

        int choice = ioInterface.showOptionsAndGetAnswer(movesList);

        visibleMap.moveWalker(possibleMoves.get(choice - 1));
        return true;
    }

    private boolean useSkill(){
        ArrayList<Skill> skills = walker.getSkills();
        ArrayList<String> nameList = new ArrayList<>();

        for(Skill skill: skills){
            nameList.add(skill.getSkillName());
        }

        int choice = ioInterface.showOptionsAndGetAnswer(nameList);

        if (choice != 0){
            Skill chosenSkill = skills.get(choice - 1);
            ArrayList<Pair<Walker, Coordinate>> targets = chosenSkill.getTargets(visibleMap);
            ArrayList<String> targetList = new ArrayList<>();

            for(Pair<Walker, Coordinate> pair : targets){
                Walker targetWalker = pair.getKey();
                Coordinate coordinate = pair.getValue();
                targetList.add(targetWalker.getName() + " - " + coordinate.toString());
            }

            choice = ioInterface.showOptionsAndGetAnswer(targetList);

            if (choice != 0){
                Walker targetWalker = targets.get(choice - 1).getKey();
                chosenSkill.use(visibleMap, targetWalker);
                return true;
            }else
                return false;
        }
        else
            return false;
    }
}
