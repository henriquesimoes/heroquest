package br.unicamp.ic.mc322.heroquest.walker.manager.ai;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Behavior {
    protected WalkerManager walkerManager;
    protected ArrayList<Walker> enemies;

    public Behavior(){
        enemies = new ArrayList<>();
    }

    public void setWalkerManager(WalkerManager walkerManager){
        this.walkerManager = walkerManager;
    }
}
