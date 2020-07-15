
package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.loader.MapParser;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.shuffle;

public class MapGenerator {
    private final int BSP_ITERATIONS = 4;
    private final int GRID_HEIGHT = 31;
    private final int GRID_WIDTH = 80;

    private final int ROOM_MIN_WIDTH = 11;
    private final int ROOM_MIN_HEIGHT = 7;

    private final int NUMBER_OF_CHESTS = 15;

    private char[][] grid;
    private ArrayList<GridContainer> gridSections;
    private ArrayList<RoomStructure> rooms;

    public MapGenerator() {
        rooms = new ArrayList<>();
    }

    public MapBuilder generate() {
        createGrid();
        createRandomRooms();
        createMatrixGrid();

        MapBuilder builder = new MapBuilder();

        createStructure(builder);

        Collection<Chest> chests = generateChests();

        for (Chest chest : chests)
            builder.add(chest);

        return builder;
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
            Coordinate roomCoord = room.getTopLeftCoordinate();
            Dimension roomDimensions = room.getDimension();

            for (int i = roomCoord.getY(); i < roomCoord.getY() + roomDimensions.getHeight(); i++) {
                for (int j = roomCoord.getX(); j < roomCoord.getX() + roomDimensions.getWidth(); j++) {
                    grid[i][j] = isOnBorder(roomCoord, roomDimensions, i, j) ? '#': ' ';
                }
            }
        }
    }

    private Collection<Chest> generateChests(){
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for (int y = 0; y < GRID_HEIGHT; y++)
            for (int x = 0; x < GRID_WIDTH; x++)
                coordinates.add(new Coordinate(x, y));

        shuffle(coordinates);

        int remaining = NUMBER_OF_CHESTS;
        Set<Coordinate> chosenCoordinates = new HashSet<>();

        for (Coordinate coordinate : coordinates){
            int x = coordinate.getX();
            int y = coordinate.getY();

            if (grid[y][x] == ' '){
                Coordinate[] neighbors = coordinate.getAdjacentNeighborCoordinates();
                int numberOfNeighborsEmpty = 0;

                for (Coordinate neighbor : neighbors)
                    if (isEmpty(neighbor) && !chosenCoordinates.contains(neighbor))
                        numberOfNeighborsEmpty++;

                if (numberOfNeighborsEmpty == 5) {
                    chosenCoordinates.add(coordinate);
                    remaining--;
                    if(remaining <= 0)
                        break;
                }

            }
        }

        Collection<Chest> chests = new ArrayList<>();
        for (Coordinate coordinate : chosenCoordinates)
            chests.add(new Chest(coordinate));

        return chests;
    }

    private boolean isEmpty(Coordinate coordinate){
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT && grid[y][x] == ' ';
    }

    private boolean isOnBorder(Coordinate coordinates, Dimension dimensions, int i, int j) {
        if (i == coordinates.getY() || j == coordinates.getX()
                || i == (coordinates.getY() + dimensions.getHeight() - 1)
                || j == (coordinates.getX() + dimensions.getWidth() - 1)) {
            return  true;
        }

        return false;
    }

    private void createStructure(MapBuilder builder) {
        Dimension dimension = new Dimension(GRID_WIDTH, GRID_HEIGHT);

        for (Coordinate coordinate : RegionSelector.getPlaneRegion(dimension)) {
            Coordinate relative = coordinate.toRelative();

            MapParser.parseAndAdd(grid[relative.getY()][relative.getX()], coordinate, builder);
        }

        builder.buildStructure();
    }
}
