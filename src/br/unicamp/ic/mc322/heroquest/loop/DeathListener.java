package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public interface DeathListener {

    void notifyWalkerDeath(WalkerManager walkerManager);
}
