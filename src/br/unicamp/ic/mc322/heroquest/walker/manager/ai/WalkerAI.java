package br.unicamp.ic.mc322.heroquest.walker.manager.ai;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement.MovementBehavior;

public class WalkerAI extends WalkerManager {
    private MovementBehavior movementBehavior;
    private AttackBehavior attackBehavior;

    public WalkerAI(MovementBehavior movementBehavior, AttackBehavior attackBehavior) {
        this.movementBehavior = movementBehavior;
        this.attackBehavior = attackBehavior;
    }

    @Override
    public void playTurn() {

    }
}
