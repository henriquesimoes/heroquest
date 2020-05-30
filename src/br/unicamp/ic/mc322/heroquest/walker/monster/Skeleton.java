package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attackBehavior.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movimentBehavior.RandomMovement;

public abstract class Skeleton extends Monster {
    Skeleton(){
        super();
        WalkerManager walkerManager = new WalkerAI(new RandomMovement(), new Bloodthirsty());
    }
}
