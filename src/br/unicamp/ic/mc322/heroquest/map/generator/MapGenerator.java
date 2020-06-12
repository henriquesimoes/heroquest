package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.Room;
import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
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

    private java.util.Map<String, RoomStructure> rooms;

    public MapGenerator(){
        rooms = new HashMap<>();
    }

    public void generate() {
        createGrid();
        generateRooms();
        createMatrixGrid();
        print();
    }

    private void createGrid() {
        gridSections = new BSPGrid(GRID_WIDTH, GRID_HEIGHT, BSP_ITERATIONS)
                .getPartitionedGrid();
    }

    private void generateRooms() {
        int id = 0;
        for (GridContainer container : gridSections) {
            Dimension dimensions = getRandomRoomDimensions(container);
            Coordinate roomCoordinates = getRandomRoomCoordinates(container, dimensions);

            rooms.put("ID" + id, new RoomStructure(dimensions, roomCoordinates, id));
            id += 1;
        }
    }

    private Dimension getRandomRoomDimensions(GridContainer container) {
        int dimensionX = Math.max(ROOM_MIN_WIDTH, random.nextInt(container.getDimensionX()) - 2);
        int dimensionY = Math.max(ROOM_MIN_HEIGHT, random.nextInt(container.getDimensionY()) - 2);

        return (new Dimension(dimensionX, dimensionY));
    }

    private Coordinate getRandomRoomCoordinates(GridContainer container, Dimension dimensions) {
        Coordinate containerCoordinates = container.getTopLeftCornerCoordinate();
        if (container.getDimensionY() - dimensions.getHeight() < 0 || container.getDimensionX() - dimensions.getWidth() < 0) {
            System.out.println("teste");
        }
        int coordY = random.nextInt(container.getDimensionY() - dimensions.getHeight() + 1) + containerCoordinates.getY();
        int coordX = random.nextInt(container.getDimensionX() - dimensions.getWidth() + 1) + containerCoordinates.getX();

        return (new Coordinate(coordX, coordY));
    }
    
    private void createMatrixGrid() {
        grid = new char[GRID_HEIGHT][GRID_WIDTH];

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = '#';
            }
        }

        for (Map.Entry<String, RoomStructure> room : rooms.entrySet()) {
            RoomStructure currentRoom = room.getValue();
            Coordinate roomCoord = currentRoom.getRoomTopLeftCoordinates();
            Dimension roomDimensions = currentRoom.getRoomDimension();

            for (int i = roomCoord.getY(); i < roomCoord.getY() + roomDimensions.getHeight(); i++) {
                if (i == roomCoord.getY() || i == (roomCoord.getY() + roomDimensions.getHeight() - 1)) {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + roomDimensions.getWidth(); j++) {
                        grid[i][j] = '/';
                    }
                }
                else {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + currentRoom.getRoomDimension().getWidth(); j++) {
                        grid[i][j] = ' ';
                    }
                    grid[i][roomCoord.getX()] = '/';
                    grid[i][roomCoord.getX() + currentRoom.getRoomDimension().getWidth() - 1] = '/';
                }
            }
        }
        for (Map.Entry<String, RoomStructure> room : rooms.entrySet()) {
            System.out.println("Coords (" + room.getValue().getRoomTopLeftCoordinates().getX() + ", " + room.getValue().getRoomTopLeftCoordinates().getY() + ")" +
                    "- Size:" + room.getValue().getRoomDimension().getWidth() + " x " +  room.getValue().getRoomDimension().getHeight());
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
