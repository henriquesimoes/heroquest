package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class GameMonitor {
    private static GameMonitor gameMonitor;
    private ArrayList<GameListener> gameListeners;

    private GameMonitor() {
        gameListeners = new ArrayList<>();
    }

    public static GameMonitor getInstance() {
        if (gameMonitor == null)
            gameMonitor = new GameMonitor();

        return gameMonitor;
    }

    public void notifyDeath(Walker walker) {
        for (GameListener listener : gameListeners)
            listener.notifyWalkerDeath(walker);
    }

    public void addListener(GameListener listener) {
        gameListeners.add(listener);
    }

    public void notifyDamage(Walker walker, int damage) {
        for (GameListener listener : gameListeners)
            listener.notifyWalkerDamage(walker, damage);
    }

    public void add(Walker walker) {
        for (GameListener listener : gameListeners)
            listener.add(walker);
    }
}
