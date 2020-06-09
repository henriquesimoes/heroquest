package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.walker.manager.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement.RandomMovement;

public abstract class Skeleton extends Monster {
    Skeleton() {
        walkerManager = new WalkerAI(this, new RandomMovement(), new Bloodthirsty());
    }
}
