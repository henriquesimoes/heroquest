package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public interface GameListener {

    void notifyWalkerDeath(WalkerManager walkerManager);

    void notifyWalkerDamage(WalkerManager manager, int damage);
}
