package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.Room;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.util.tree.BSPTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapGenerator {
    private final int BSP_ITERATIONS = 4;
    private final int GRID_HEIGHT = 31;
    private final int GRID_WIDTH = 101;

    private final int ROOM_MIN_WIDTH = 11;
    private final int ROOM_MIN_HEIGHT = 7;

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
        generateRooms();
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
        int idCounter = 0;
        for (GridContainer container : gridSections) {
            Pair<Integer, Integer> dimensions = getRandomRoomDimensions(container);
            Coordinate roomCoordinates = getRandomRoomCoordinates(container, dimensions);

            rooms.put("ID" + idCounter, new Room(dimensions, roomCoordinates));
            idCounter += 1;
//            System.out.println(rooms.get("ID" + idCounter).getRoomDimension().getKey() + " " + rooms.get("ID" + idCounter).getRoomDimension().getValue());
        }
    }

    private Pair<Integer, Integer> getRandomRoomDimensions(GridContainer container) {
        int dimensionX = Math.max(ROOM_MIN_WIDTH, random.nextInt(container.getDimensionX()) - 2);
        int dimensionY = Math.max(ROOM_MIN_HEIGHT, random.nextInt(container.getDimensionY()) - 2);

        return (new Pair<>(dimensionX, dimensionY));
    }

    private Coordinate getRandomRoomCoordinates(GridContainer container, Pair<Integer, Integer> dimensions) {
        Coordinate containerCoordinates = container.getTopLeftCornerCoordinate();
        if (container.getDimensionY() - dimensions.getValue() < 0 || container.getDimensionX() - dimensions.getKey() < 0) {
            System.out.println("teste");
        }
        int coordY = random.nextInt(container.getDimensionY() - dimensions.getValue() + 1) + containerCoordinates.getY();
        int coordX = random.nextInt(container.getDimensionX() - dimensions.getKey() + 1) + containerCoordinates.getX();

        return (new Coordinate(coordX, coordY));
    }

    private void createMatrixGrid() {
        grid = new char[GRID_HEIGHT][GRID_WIDTH];

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = '#';
            }
        }

        for (Map.Entry<String, Room> room : rooms.entrySet()) {
            Coordinate roomCoord = room.getValue().getTopLeftCoordinates();

            for (int i = roomCoord.getY(); i < roomCoord.getY() + room.getValue().getRoomDimension().getValue(); i++) {
                if (i == roomCoord.getY() || i == (roomCoord.getY() + room.getValue().getRoomDimension().getValue() - 1)) {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + room.getValue().getRoomDimension().getKey(); j++) {
                        grid[i][j] = 'B';
                    }
                }
                else {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + room.getValue().getRoomDimension().getKey(); j++) {
                        grid[i][j] = ' ';
                    }
                    grid[i][roomCoord.getX()] = 'B';
                    grid[i][roomCoord.getX() + room.getValue().getRoomDimension().getKey() - 1] = 'B';
                }
            }
        }
        for (Map.Entry<String, Room> room : rooms.entrySet()) {
            System.out.println("Coords (" + room.getValue().getTopLeftCoordinates().getX() + ", " + room.getValue().getTopLeftCoordinates().getY() + ")" +
                    "- Size:" + room.getValue().getRoomDimension().getKey() + " x " +  room.getValue().getRoomDimension().getValue());
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
