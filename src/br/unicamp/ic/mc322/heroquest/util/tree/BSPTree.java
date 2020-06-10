package br.unicamp.ic.mc322.heroquest.util.tree;

import br.unicamp.ic.mc322.heroquest.map.generator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.random.Random;

import java.util.ArrayList;

public class BSPTree {
    private final int GRID_MIN_WIDTH = 9;
    private final int GRID_MIN_HEIGHT = 5;

    private Leaf<GridContainer> root;
    private GridContainer currentContainer;
    private Random random = new Random();


    public BSPTree(GridContainer grid) {
        this.root = new Leaf<>(grid);
        this.currentContainer = root.getData();
    }

    private BSPTree(Leaf<GridContainer> gridNode) {
        this.root = gridNode;
        this.currentContainer = root.getData();
    }

    public void runBSP(int numberOfIterations) {
        if (numberOfIterations > 0) {
            splitGrid();

            if (root.getRightChild() != null) {
                new BSPTree(root.getLeftChild()).runBSP(numberOfIterations - 1);
                new BSPTree(root.getRightChild()).runBSP(numberOfIterations - 1);
            }
            else
                return;
        }
    }

    private boolean splitGrid() {
        if (!root.isBaseLeaf())
            return false;

        boolean splitHorizontal = random.nextBoolean();

        int maxSplitValueInDirection = getMaxSplitValue(splitHorizontal);

        if (maxSplitValueInDirection <= (splitHorizontal ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH))
            return false;

        int splitPoint = getSplitPoint(splitHorizontal, maxSplitValueInDirection);
        Coordinate currentContainerCoords = currentContainer.getTopLeftCornerCoordinate();

        if (splitHorizontal) {
            GridContainer leftChildContainer = new GridContainer(currentContainer.getDimensionX(), splitPoint,
                    currentContainerCoords.getX(), currentContainerCoords.getY());

            GridContainer rightChildContainer = new GridContainer(
                    currentContainer.getDimensionX(),
                    currentContainer.getDimensionY() - splitPoint,
                    currentContainerCoords.getX(),
                    currentContainerCoords.getY() + (currentContainer.getDimensionY() - splitPoint));

            root.setLeftChild(new Leaf<>(leftChildContainer));
            root.setRightChild(new Leaf<>(rightChildContainer));
        }
        else {
            GridContainer leftChildContainer = new GridContainer(splitPoint, currentContainer.getDimensionY(),
                    currentContainerCoords.getX(), currentContainerCoords.getY());

            GridContainer rightChildContainer = new GridContainer(
                    currentContainer.getDimensionX() - splitPoint,
                    currentContainer.getDimensionY(),
                    currentContainerCoords.getX() + splitPoint,
                    currentContainerCoords.getY());

            root.setLeftChild(new Leaf<>(leftChildContainer));
            root.setRightChild(new Leaf<>(rightChildContainer));

        }

        return true;
    }

    private int getMaxSplitValue(boolean splitHorizontal) {
        return (splitHorizontal ? (currentContainer .getDimensionY() - GRID_MIN_HEIGHT)
                : (currentContainer.getDimensionX() - GRID_MIN_WIDTH));
    }

    private int getSplitPoint(boolean splitHorizontal, int maxSplitValue) {
        int splitPoint = random.nextInt(maxSplitValue);

        return Math.max((splitHorizontal ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH), splitPoint);
    }

    private void getPartitions(ArrayList<GridContainer> partitionsArray, Leaf<GridContainer> node) {
        if (node.isBaseLeaf()) {
            partitionsArray.add(node.getData());
            return;
        }

        getPartitions(partitionsArray, node.getLeftChild());
        getPartitions(partitionsArray, node.getRightChild());
    }

    public ArrayList<GridContainer> getPartitionedGrid() {
        ArrayList<GridContainer> partitionedGrid = new ArrayList<>();

        getPartitions(partitionedGrid, root);

        return partitionedGrid;
    }

}