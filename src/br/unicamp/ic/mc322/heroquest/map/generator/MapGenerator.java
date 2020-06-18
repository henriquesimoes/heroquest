package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.BSPGrid;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.generator.pathgenerator.PathGenerator;
import br.unicamp.ic.mc322.heroquest.map.generator.roomgenerator.RoomGenerator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapGenerator {
    private final int BSP_ITERATIONS = 4;
    private final int GRID_HEIGHT = 31;
    private final int GRID_WIDTH = 80;

    private final int ROOM_MIN_WIDTH = 11;
    private final int ROOM_MIN_HEIGHT = 7;

    private char[][] grid;
    private ArrayList<GridContainer> gridSections;
    private ArrayList<RoomStructure> rooms;

    public MapGenerator(){
        rooms = new ArrayList<>();
    }

    public void generate() {
        createGrid();
        createRandomRooms();
        createMatrixGrid();
        print();
    }

    private void createGrid() {
        gridSections = new BSPGrid(GRID_WIDTH, GRID_HEIGHT, BSP_ITERATIONS)
                .getPartitionedGrid();
    }

    private void createRandomRooms() {
        RoomGenerator randomRooms = new RoomGenerator(gridSections, ROOM_MIN_WIDTH, ROOM_MIN_HEIGHT);
        rooms = randomRooms.createRandomRooms();
    }

    private void createMatrixGrid(){
        grid = new char[GRID_HEIGHT][GRID_WIDTH];

        fillGridWithWalls();
        fillGridWithRoomsAreas();
        addPathsBetweenRooms();
    }

    private void addPathsBetweenRooms() {
        new PathGenerator(grid, rooms, gridSections).createPaths();
    }

    private void fillGridWithWalls() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = '#';
            }
        }
    }

    private void fillGridWithRoomsAreas() {
        for (RoomStructure room : rooms) {
            Coordinate roomCoord = room.getRoomTopLeftCoordinates();
            Dimension roomDimensions = room.getRoomDimension();

            for (int i = roomCoord.getY(); i < roomCoord.getY() + roomDimensions.getHeight(); i++) {
                if (i == roomCoord.getY() || i == (roomCoord.getY() + roomDimensions.getHeight() - 1)) {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + roomDimensions.getWidth(); j++) {
                        grid[i][j] = '#';
                    }
                }
                else {
                    for (int j = roomCoord.getX(); j < roomCoord.getX() + room.getRoomDimension().getWidth(); j++) {
                        grid[i][j] = ' ';
                    }
                    grid[i][roomCoord.getX()] = '#';
                    grid[i][roomCoord.getX() + room.getRoomDimension().getWidth() - 1] = '#';
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.print('\n');
        }
    }
}
