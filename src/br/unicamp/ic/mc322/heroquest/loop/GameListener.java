package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface GameListener {

    void notifyWalkerDeath(Walker walker);

    void notifyWalkerDamage(Walker walker, int damage);

    void add(Walker walker);
}
