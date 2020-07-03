package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.BSPGrid;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.generator.pathgenerator.PathGenerator;
import br.unicamp.ic.mc322.heroquest.map.generator.roomgenerator.RoomGenerator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.loader.MapParser;

import java.util.ArrayList;

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

    public Map generate() {
        createGrid();
        createRandomRooms();
        createMatrixGrid();

        return new Map(buildStructure());
    }

    private void createGrid() {
        gridSections = new BSPGrid(GRID_WIDTH, GRID_HEIGHT, BSP_ITERATIONS)
                .getPartitionedGrid();
    }

    private void createRandomRooms() {
        RoomGenerator randomRooms = new RoomGenerator(gridSections, ROOM_MIN_WIDTH, ROOM_MIN_HEIGHT);
        rooms = randomRooms.createRandomRooms();
    }

    private void createMatrixGrid() {
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
                for (int j = roomCoord.getX(); j < roomCoord.getX() + roomDimensions.getWidth(); j++) {
                    grid[i][j] = isOnBorder(roomCoord, roomDimensions, i, j) ? '#': ' ';
                }
            }
        }
    }

    private boolean isOnBorder(Coordinate coordinates, Dimension dimensions, int i, int j) {
        if (i == coordinates.getY() || j == coordinates.getX()
                || i == (coordinates.getY() + dimensions.getHeight() - 1)
                || j == (coordinates.getX() + dimensions.getWidth() - 1)) {
            return  true;
        }

        return false;
    }

    private MapStructure buildStructure() {
        MapStructure structure = new MapStructure();
        Coordinate origin = Coordinate.getOrigin();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Coordinate coordinate = Coordinate.shift(origin, j, i);

                structure.add(MapParser.parse(grid[i][j], coordinate));
            }
        }

        return structure;
    }
}
