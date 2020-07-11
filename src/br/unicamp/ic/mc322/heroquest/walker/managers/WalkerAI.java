package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
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
        useItems();
        boolean successUseSkill = useSkill();
        makeMove();
        if (!successUseSkill)
            useSkill();
        useItems();
    }

    @Override
    protected CollectableItem chooseItem(ArrayList<CollectableItem> items) {
        return items.size() == 0 ? null : items.get(Randomizer.nextInt(items.size()));
    }

    @Override
    protected Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        return movementBehavior.chooseMove(possibleMoves);
    }

    @Override
    protected Skill chooseSkill(ArrayList<Skill> skills) {
        ArrayList<Skill> skillWithValidTargets = new ArrayList<>();

        // store in skillTarget each skill with a valid target
        for (Skill skill : skills) {
            ArrayList<MapObject> targets = skill.getTargets();
            if (targets.size() != 0)
                skillWithValidTargets.add(skill);
        }

        return skillWithValidTargets.size() == 0 ? null : attackBehavior.chooseSkill(skillWithValidTargets);
    }

    @Override
    protected MapObject chooseTarget(ArrayList<MapObject> targets) {
        return attackBehavior.chooseTarget(targets);
    }

    @Override
    public void showMessage(String message) {

    }
}
