package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

import java.util.ArrayList;

public abstract class Behavior {
    protected WalkerManager walkerManager;
    protected ArrayList<Walker> enemies;

    Behavior() {
        enemies = new ArrayList<>();
    }

    void setWalkerManager(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
    }
}
