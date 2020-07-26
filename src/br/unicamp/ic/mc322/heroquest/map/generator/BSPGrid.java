package br.unicamp.ic.mc322.heroquest.map.generator;

import java.util.ArrayList;

/**
 * It is used a GridContainer class that is partitioned by a BSPTree class,
 * generating an array of GridContainers with different dimensions and positions based on the
 * original GridContainer shape.
 */
class BSPGrid {
    private int gridHeight;
    private int gridWidth;
    private int bspIterations;

    public BSPGrid(int gridWidth, int gridHeight, int bspIterations) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.bspIterations = bspIterations;
    }

    public ArrayList<GridContainer> getPartitionedGrid() {
        return getBestGrid();
    }

    /**
     * @return the grid with more zones inside after 20 attempts using a BSPTree,
     * each time using a new instance of this tree kind.
     */
    private ArrayList<GridContainer> getBestGrid() {
        ArrayList<GridContainer> bestGrid = null;

        for (int i = 0; i < 20; i++) {
            BSPTree grid = createBSP();
            ArrayList<GridContainer> comparableGrid = grid.getPartitionedGrid();
            if (bestGrid == null || comparableGrid.size() > bestGrid.size())
                bestGrid = comparableGrid;
        }

        return bestGrid;
    }

    private BSPTree createBSP() {
        GridContainer gridSetup = new GridContainer(gridWidth, gridHeight, 0, 0);
        BSPTree grid = new BSPTree(gridSetup);
        grid.runBSP(bspIterations);

        return grid;
    }
}
