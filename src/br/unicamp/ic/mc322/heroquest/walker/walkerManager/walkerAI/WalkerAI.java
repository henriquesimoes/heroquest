package br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI;

import br.unicamp.ic.mc322.heroquest.walker.walkerManager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.attackBehavior.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.movimentBehavior.MovimentBehavior;

public class WalkerAI extends WalkerManager {
    private MovimentBehavior movimentBehavior;
    private AttackBehavior attackBehavior;

    public WalkerAI(MovimentBehavior movimentBehavior, AttackBehavior attackBehavior){
        this.movimentBehavior = movimentBehavior;
        this.attackBehavior = attackBehavior;
    }
}
