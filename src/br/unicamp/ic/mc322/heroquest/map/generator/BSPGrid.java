package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.util.tree.BSPTree;

import java.util.ArrayList;

public class BSPGrid {
    private int gridHeight;
    private int gridWidth;
    private int bspIterations;

    public BSPGrid(int gridWidth, int gridHeight, int bspIterations){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.bspIterations = bspIterations;
    }

    protected ArrayList<GridContainer> getPartitionedGrid() {
        return getBestGrid();
    }

    private ArrayList<GridContainer> getBestGrid() {
        ArrayList<GridContainer> bestGrid = null;

        int i = 0;
        do {
            BSPTree grid = createBSP();
            ArrayList<GridContainer> comparableGrid = grid.getPartitionedGrid();

            if (bestGrid == null)
                bestGrid = comparableGrid;
            else if (comparableGrid.size() > bestGrid.size())
                bestGrid = comparableGrid;

            i++;
        } while (i < 20);

        return bestGrid;
    }

    private BSPTree createBSP() {
        GridContainer gridSetup = new GridContainer(gridWidth, gridHeight, 0, 0);
        BSPTree grid = new BSPTree(gridSetup);
        grid.runBSP(bspIterations);

        return grid;
    }
}
