package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class DeathMonitor {
    static DeathMonitor deathMonitor;
    ArrayList<DeathListener> listeners;

    private DeathMonitor(){
        listeners = new ArrayList<>();
    }

    public static DeathMonitor getInstance(){
        if (deathMonitor == null)
            deathMonitor = new DeathMonitor();
        return deathMonitor;
    }

    public void notify(Walker walker){
        for (DeathListener listener : listeners)
            listener.notifyWalkerDeath(walker.getManager());
    }

    public void addListener(DeathListener listener){
        listeners.add(listener);
    }
}
