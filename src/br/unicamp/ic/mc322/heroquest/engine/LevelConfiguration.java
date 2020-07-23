package br.unicamp.ic.mc322.heroquest.engine;

public interface LevelConfiguration {
    /**
     * Defines the number of monsters on the map given its area.
     *
     * @param area map area
     * @return percentage
     */
    int getNumberOfMonsters(int area);
}
