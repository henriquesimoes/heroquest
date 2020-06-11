package br.unicamp.ic.mc322.heroquest.walker.manager.ai;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement.MovementBehavior;

import java.util.ArrayList;

public class WalkerAI extends WalkerManager {
    private MovementBehavior movementBehavior;
    private AttackBehavior attackBehavior;

    public WalkerAI(Walker walker, MovementBehavior movementBehavior, AttackBehavior attackBehavior) {
        super(walker);
        this.movementBehavior = movementBehavior;
        this.attackBehavior = attackBehavior;
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
    protected int chooseItem(ArrayList<CollectableItem> items) {
        if (items.size() == 0)
            return 0;
        return Randomizer.randInt(1, items.size()); // choice item indexed by 1
    }

    @Override
    protected int chooseMove(ArrayList<Coordinate> possibleMoves) {
        return movementBehavior.chooseMove(this, possibleMoves);
    }

    @Override
    protected int chooseSkill(ArrayList<Skill> skills){
        ArrayList<Skill> skillWithValidTargets = new ArrayList<>();

        // store in skillTarget each skill with a valid target
        for (Skill skill : skills){
            ArrayList<MapObject> targets = skill.getTargets(this);
            if (targets.size() != 0)
                skillWithValidTargets.add(skill);
        }

        // nothing skill has a valid target
        if (skillWithValidTargets.size() == 0)
            return 0;

        return attackBehavior.chooseSkill(skillWithValidTargets);
    }

    @Override
    protected int chooseTargetSkill(ArrayList<MapObject> targets) {
        return attackBehavior.chooseTarget(targets);
    }
}
