package br.unicamp.ic.mc322.heroquest.walker.manager.ai;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement.MovementBehavior;

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

    }
}
