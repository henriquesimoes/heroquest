package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.managers.MoveAction;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseItemAction;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseSkillAction;

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
        UseItemAction useItemAction = new UseItemAIAction(this);
        UseSkillAction useSkillAction = new UseSkillAIAction(this);
        MoveAction moveAction = new MoveAIAction(this);

        useItemAction.execute();
        boolean successUseSkill = useSkillAction.execute();
        moveAction.execute();
        if (!successUseSkill)
            useSkillAction.execute();
        useItemAction.execute();
    }

    @Override
    public MapObject chooseTarget(MapObject[] targets) {
        return attackBehavior.chooseTarget(targets);
    }

    @Override
    public void showMessage(String message) {

    }

    MovementBehavior getMovementBehavior() {
        return movementBehavior;
    }

    AttackBehavior getAttackBehavior() {
        return attackBehavior;
    }
}
