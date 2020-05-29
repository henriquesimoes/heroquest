package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.attackBehavior.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.movimentBehavior.RandomMovement;

public abstract class Skeleton extends Monster {

    Skeleton(){
        super();
        walkerManager = new WalkerAI(new RandomMovement(), new Bloodthirsty());
    }
}
