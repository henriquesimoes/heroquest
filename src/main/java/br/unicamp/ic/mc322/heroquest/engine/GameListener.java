package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface GameListener {

    /**
     * Notifies the listeners that the walker no longer takes
     * turns.
     *
     * @param walker - dead walker
     */
    void notifyWalkerDeath(Walker walker);

    /**
     * Notifies all listeners the damage the walker has suffered.
     *
     * @param walker - walker that suffered the damage
     * @param causer - causer name
     * @param damage - damage value
     */
    void notifyWalkerDamage(Walker walker, String causer, int damage);

    /**
     * Adds a new walker into the game.
     * Usually a monster coming out of strange places.
     *
     * @param walker - walker to be processed by the listeners
     */
    void add(Walker walker);
}
