package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.HashSet;

public class GameMonitor {
    private static GameMonitor gameMonitor;
    private HashSet<GameListener> listeners;

    private GameMonitor() {
        listeners = new HashSet<>();
    }

    public static GameMonitor getInstance() {
        if (gameMonitor == null)
            gameMonitor = new GameMonitor();

        return gameMonitor;
    }

    public void subscribe(GameListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(GameListener listener) {
        listeners.remove(listener);
    }

    public void notifyDeath(Walker walker) {
        for (GameListener listener : listeners)
            listener.notifyWalkerDeath(walker);
    }

    public void notifyDamage(Walker walker, String causer, int damage) {
        for (GameListener listener : listeners)
            listener.notifyWalkerDamage(walker, causer, damage);
    }

    /**
     * Notifies that a new walker is coming into the game.
     * This might occur when a walker gets out of a hidden
     * place, such as a chest.
     *
     * @param walker Upcoming walker
     */
    public void notifyUpcoming(Walker walker) {
        for (GameListener listener : listeners)
            listener.add(walker);
    }
}
