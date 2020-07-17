package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.MovementBehavior;

import java.util.ArrayList;

public class WalkerAI extends WalkerManager {
    private MovementBehavior movementBehavior;
    private AttackBehavior attackBehavior;

    public WalkerAI(MovementBehavior movementBehavior, AttackBehavior attackBehavior) {
        this.movementBehavior = movementBehavior;
        this.attackBehavior = attackBehavior;
        this.movementBehavior.setWalkerManager(this);
        this.attackBehavior.setWalkerManager(this);
    }

    @Override
    public void playTurn() {
        useItem();
        boolean successUseSkill = useSkill();
        makeMove();
        if (!successUseSkill)
            useSkill();
        useItem();
    }

    protected boolean makeMove() {
        int limitPositionInMove = walker.getPositionLimitInMovement();
        Region region = regionSelector.getLimitedRegion(limitPositionInMove, true);

        ArrayList<Coordinate> possibleMoves = region.toArrayList();

        Coordinate chosenMove = chooseMove(possibleMoves);
        if (chosenMove != null)
            moveWalker(chosenMove);

        return true;
    }

    @Override
    protected CollectableItem chooseItem(CollectableItem[] items) {
        return items.length == 0 ? null : items[Randomizer.nextInt(items.length)];
    }

    protected Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        return movementBehavior.chooseMove(possibleMoves);
    }

    @Override
    protected Skill chooseSkill(Skill[] skills) {
        ArrayList<Skill> skillWithValidTargets = new ArrayList<>();

        // store in skillTarget each skill with a valid target
        for (Skill skill : skills) {
            MapObject[] targets = skill.getTargets();
            if (targets.length != 0)
                skillWithValidTargets.add(skill);
        }

        return skillWithValidTargets.size() == 0 ? null : attackBehavior.chooseSkill((Skill[]) skillWithValidTargets.toArray());
    }

    @Override
    protected MapObject chooseTarget(MapObject[] targets) {
        return attackBehavior.chooseTarget(targets);
    }

    @Override
    public void showMessage(String message) {

    }
}
