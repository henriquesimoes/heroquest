package br.unicamp.ic.mc322.heroquest.util.tree;

import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

public class BSPTree {
    private final int GRID_MIN_WIDTH = 13;
    private final int GRID_MIN_HEIGHT = 11;

    private final Node<GridContainer> root;
    private final GridContainer currentContainer;
    private final Coordinate currentContainerCoords;
    private GridContainer leftChildContainer;
    private GridContainer rightChildContainer;

    public BSPTree(GridContainer grid) {
        this.root = new Node<>(grid);
        this.currentContainer = root.getData();
        this.currentContainerCoords = currentContainer.getTopLeftCornerCoordinate();
    }

    private BSPTree(Node<GridContainer> gridNode) {
        this.root = gridNode;
        this.currentContainer = root.getData();
        this.currentContainerCoords = currentContainer.getTopLeftCornerCoordinate();
    }

    public void runBSP(int numberOfIterations) {
        if (numberOfIterations > 0) {
            splitGrid();

            if (root.getRightChild() != null) {
                new BSPTree(root.getLeftChild()).runBSP(numberOfIterations - 1);
                new BSPTree(root.getRightChild()).runBSP(numberOfIterations - 1);
            }
        }
    }

    private void splitGrid() {
        if (!root.isLeaf())
            return;

        boolean HorizontalSplit = Randomizer.nextBoolean();

        int maxSplitValueInDirection = getMaxSplitValue(HorizontalSplit);

        if (maxSplitValueInDirection <= (HorizontalSplit ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH))
            return;

        int splitPoint = getSplitPoint(HorizontalSplit, maxSplitValueInDirection);

        if (HorizontalSplit) {
            splitHorizontal(splitPoint);
        }
        else {
            splitVertical(splitPoint);
        }

        root.setLeftChild(new Node<>(leftChildContainer));
        root.setRightChild(new Node<>(rightChildContainer));

    }

    private void splitHorizontal(int splitPoint) {
        leftChildContainer = new GridContainer(
                currentContainer.getDimensionX(),
                splitPoint,
                currentContainerCoords.getX(),
                currentContainerCoords.getY());

        rightChildContainer = new GridContainer(
                currentContainer.getDimensionX(),
                currentContainer.getDimensionY() - splitPoint - 2,
                currentContainerCoords.getX(),
                currentContainerCoords.getY() + splitPoint + 2);
    }

    private void splitVertical(int splitPoint) {
        leftChildContainer = new GridContainer(splitPoint, currentContainer.getDimensionY(),
                currentContainerCoords.getX(), currentContainerCoords.getY());

        rightChildContainer = new GridContainer(
                currentContainer.getDimensionX() - splitPoint - 2,
                currentContainer.getDimensionY(),
                currentContainerCoords.getX() + splitPoint + 2,
                currentContainerCoords.getY());
    }

    private int getMaxSplitValue(boolean splitHorizontal) {
        return (splitHorizontal ? (currentContainer.getDimensionY() - GRID_MIN_HEIGHT)
                : (currentContainer.getDimensionX() - GRID_MIN_WIDTH));
    }

    private int getSplitPoint(boolean splitHorizontal, int maxSplitValue) {
        int splitPoint = Randomizer.nextInt(maxSplitValue - 1);
        return Math.max((splitHorizontal ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH), splitPoint);
    }

    private void getPartitions(ArrayList<GridContainer> partitionsArray, Node<GridContainer> node) {
        if (node.isLeaf()) {
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