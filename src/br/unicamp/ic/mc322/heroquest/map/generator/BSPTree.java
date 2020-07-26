package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.util.tree.Node;

import java.util.ArrayList;

/**
 * An algorithm logic based on trees that takes a GridContainer with pre-defined dimensions
 * and make a binary partition of the container in each iteration, either in horizontal or in vertical, in random
 * coordinates.
 * In summary, if you execute n iterations of the split algorithm, you will end up with 2^n zones
 * on the GridContainer, each one being a new GridContainer.
 *
 * There is an exception for the number of zones that you will end up with, case you define a minimum width/height,
 * because if a zone have dimensions smaller then the minimum that was defined, while running the split algorithm,
 * the partition of the container isn't made in that iteration.
 * */
class BSPTree {

    private final int GRID_MIN_WIDTH = 13;
    private final int GRID_MIN_HEIGHT = 11;

    private Node<GridContainer> root;
    private GridContainer currentContainer;
    private Coordinate currentContainerCoordinate;
    private GridContainer leftChildContainer;
    private GridContainer rightChildContainer;

    public BSPTree(GridContainer grid) {
        this.root = new Node<>(grid);
        this.currentContainer = root.getData();
        this.currentContainerCoordinate = currentContainer.getTopLeftCoordinate();
    }

    private BSPTree(Node<GridContainer> gridNode) {
        this.root = gridNode;
        this.currentContainer = root.getData();
        this.currentContainerCoordinate = currentContainer.getTopLeftCoordinate();
    }

    public void runBSP(int numberOfIterations) {
        if (numberOfIterations > 0) {
            splitGrid();

            /**
             * Takes the existent zones (that are also GridContainers) on the original GridContainer
             * that was provied to this class, creating a new tree as it being the root, what allow us
             * to run again the BSP algorithm recursively.
             * */
            if (root.getRightChild() != null) {
                new BSPTree(root.getLeftChild()).runBSP(numberOfIterations - 1);
                new BSPTree(root.getRightChild()).runBSP(numberOfIterations - 1);
            }
        }
    }

    /**
     * @return all the zones inside the original GridContainer after running the BSP algorithm
     * */
    public ArrayList<GridContainer> getPartitionedGrid() {
        ArrayList<GridContainer> partitionedGrid = new ArrayList<>();

        createPartitions(partitionedGrid, root);

        return partitionedGrid;
    }

    private void splitGrid() {
        if (!root.isLeaf())
            return;

        boolean splitHorizontally = Randomizer.nextBoolean();

        int maxSplitValueInDirection = getMaximumSplitValue(splitHorizontally);

        if (maxSplitValueInDirection <= (splitHorizontally ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH))
            return;

        int splitPoint = getSplitPoint(splitHorizontally, maxSplitValueInDirection);

        if (splitHorizontally)
            splitHorizontal(splitPoint);
        else
            splitVertical(splitPoint);

        root.setLeftChild(new Node<>(leftChildContainer));
        root.setRightChild(new Node<>(rightChildContainer));
    }

    private void splitHorizontal(int splitPoint) {
        leftChildContainer = new GridContainer(
                currentContainer.getWidth(),
                splitPoint,
                currentContainerCoordinate.getX(),
                currentContainerCoordinate.getY());

        rightChildContainer = new GridContainer(
                currentContainer.getWidth(),
                currentContainer.getHeight() - splitPoint - 2,
                currentContainerCoordinate.getX(),
                currentContainerCoordinate.getY() + splitPoint + 2);
    }

    private void splitVertical(int splitPoint) {
        leftChildContainer = new GridContainer(splitPoint, currentContainer.getHeight(),
                currentContainerCoordinate.getX(), currentContainerCoordinate.getY());

        rightChildContainer = new GridContainer(
                currentContainer.getWidth() - splitPoint - 2,
                currentContainer.getHeight(),
                currentContainerCoordinate.getX() + splitPoint + 2,
                currentContainerCoordinate.getY());
    }

    private int getMaximumSplitValue(boolean splitHorizontally) {
        return (splitHorizontally ? (currentContainer.getHeight() - GRID_MIN_HEIGHT)
                : (currentContainer.getWidth() - GRID_MIN_WIDTH));
    }

    private int getSplitPoint(boolean splitHorizontally, int maxSplitValue) {
        int splitPoint = Randomizer.nextInt(maxSplitValue - 1);
        return Math.max((splitHorizontally ? GRID_MIN_HEIGHT : GRID_MIN_WIDTH), splitPoint);
    }

    /**
     * Zones inside the original GridContainer is located in the leaves of the tree
     * */
    private void createPartitions(ArrayList<GridContainer> partitions, Node<GridContainer> node) {
        if (node.isLeaf()) {
            partitions.add(node.getData());
            return;
        }

        createPartitions(partitions, node.getLeftChild());
        createPartitions(partitions, node.getRightChild());
    }
}
