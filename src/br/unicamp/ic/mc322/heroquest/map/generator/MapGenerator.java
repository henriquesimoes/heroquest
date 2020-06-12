package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.Room;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.util.tree.BSPTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MapGenerator {
    private final int BSP_ITERATIONS = 4;
    private final int GRID_HEIGHT = 31;
    private final int GRID_WIDTH = 101;

    private final int ROOM_MIN_WIDTH = 9;
    private final int ROOM_MIN_HEIGHT = 5;
    private final int ROOM_MAX_WIDTH = 20;
    private final int ROOM_MAX_HEIGHT = 9;

    Random random = new Random();
    private char[][] grid;
    private ArrayList<GridContainer> gridSections;

    private Random randGenerator;
    private java.util.Map<String, Room> rooms;

    public MapGenerator(){
        rooms = new HashMap<>();
        randGenerator = new Random();
    }

    public void generate() {
        createGrid();
        createMatrixGrid();
        print();
    }

    private void createGrid() {
        gridSections = getPartitionedGrid();
    }

    private ArrayList<GridContainer> getPartitionedGrid() {
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
        GridContainer gridSetup = new GridContainer(GRID_WIDTH, GRID_HEIGHT, 0, 0);
        BSPTree grid = new BSPTree(gridSetup);
        grid.runBSP(BSP_ITERATIONS);

        return grid;
    }

    private void generateRooms() {
        for (GridContainer container : gridSections) {
            Pair<Integer, Integer> dimensions = getRandomRoomDimensions(container);

        }
    }

    private Pair<Integer, Integer> getRandomRoomDimensions(GridContainer container) {
        int dimensionX = Math.max(ROOM_MIN_WIDTH, random.nextInt(container.getDimensionX()));
        int dimensionY = Math.max(ROOM_MIN_HEIGHT, random.nextInt(container.getDimensionY()));

        return (new Pair<>(dimensionX, dimensionY));
    }

    private Coordinate getRandomRoomCoordinates(GridContainer container, Pair<Integer, Integer> dimensions) {
        int coordY;
        int coordX;
        return (new Coordinate());
    }

    private void createMatrixGrid() {
        grid = new char[GRID_HEIGHT][GRID_WIDTH];

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = '#';
            }
        }

        char qualquer = 'A';

        for (GridContainer container : gridSections) {
            System.out.println(container.toString());
            Coordinate coord = container.getTopLeftCornerCoordinate();

            for (int i = coord.getY(); i < coord.getY() + container.getDimensionY(); i++) {
                for (int j = coord.getX(); j < coord.getX() + container.getDimensionX(); j++) {
                    grid[i][j] = qualquer;
                }

            }
                qualquer+=1;
        }
    }


    //TODO: ainda não é a versão final

    public void print() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.print('\n');
        }
    }
}
