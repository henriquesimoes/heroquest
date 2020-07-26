package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface GameListener {

    void notifyWalkerDeath(Walker walker);

    void notifyWalkerDamage(Walker walker, String causer, int damage);

    void add(Walker walker);
}
