package br.unicamp.ic.mc322.heroquest.walker.manager.ai;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attackBehavior.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movimentBehavior.MovimentBehavior;

public class WalkerAI extends WalkerManager {
    private MovimentBehavior movimentBehavior;
    private AttackBehavior attackBehavior;

    public WalkerAI(MovimentBehavior movimentBehavior, AttackBehavior attackBehavior){
        this.movimentBehavior = movimentBehavior;
        this.attackBehavior = attackBehavior;
    }

    @Override
    public void playTurn() {

    }
}
