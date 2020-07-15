package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.loader.MapParser;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

class PathGenerator {
    private char[][] grid;
    private ArrayList<RoomStructure> rooms;
    private ArrayList<GridContainer> gridSections;
    private ArrayList<Coordinate> roomsCoordinates = new ArrayList<>();

    public PathGenerator(char[][] grid, ArrayList<RoomStructure> rooms, ArrayList<GridContainer> gridSections) {
        this.grid = grid;
        this.rooms = rooms;
        this.gridSections = gridSections;
    }

    public void createPaths() {
        calculateRoomCenterCoordinates();
        createCorridors();
        connectRoomsWithCorridors();
    }

    private void createCorridors() {
        for (GridContainer container : gridSections) {
            Coordinate topLeft = container.getTopLeftCoordinate();

            createVerticalCorridor(topLeft, container);
            createHorizontalCorridor(topLeft, container);
        }
    }

    private void createVerticalCorridor(Coordinate containerTopLeft, GridContainer container) {
        if (containerTopLeft.getX() + container.getWidth() >= grid[0].length)
            return;

        for (int i = 0; i < container.getHeight() + 2; i++) {
            if (containerTopLeft.getY() + i >= grid.length)
                break;

            if (containerTopLeft.getY() + i == 0 || containerTopLeft.getY() + i == grid.length - 1)
                continue;

            grid[containerTopLeft.getY() + i][containerTopLeft.getX() + container.getWidth()] = MapParser.FLOOR;
            grid[containerTopLeft.getY() + i][containerTopLeft.getX() + container.getWidth() + 1] = MapParser.FLOOR;
        }
    }

    private void createHorizontalCorridor(Coordinate containerTopLeft, GridContainer container) {
        if (containerTopLeft.getY() + container.getHeight() + 1 >= grid.length)
            return;

        for (int i = 0; i < container.getWidth() + 2; i++) {
            if (containerTopLeft.getX() + i >= grid[0].length)
                break;

            if (containerTopLeft.getX() + i == 0 || containerTopLeft.getX() + i == grid[0].length - 1)
                continue;

            grid[containerTopLeft.getY() + container.getHeight()][containerTopLeft.getX() + i] = MapParser.FLOOR;
            grid[containerTopLeft.getY() + container.getHeight() + 1][containerTopLeft.getX() + i] = MapParser.FLOOR;
        }
    }

    private void connectRoomsWithCorridors() {
        for (RoomStructure room : rooms) {
            tryCorridorConnection(room);
        }
    }

    private void tryCorridorConnection(RoomStructure room) {
        Coordinate roomTopLeftCoords = room.getTopLeftCoordinate();
        int roomEndXCoordinate = roomTopLeftCoords.getX() + room.getDimension().getWidth() - 1;

        tryLeftConnection(room, selectVerticalIndex(room), room.getTopLeftCoordinate().getX());

        tryRightConnection(room, selectVerticalIndex(room), roomEndXCoordinate);

    }

    private void tryLeftConnection(RoomStructure room, int selectedYCoordinate, int currentXCoordinate) {
        int initialXCoordinate = currentXCoordinate;

        while (true) {
            if (!canMakeConnection(currentXCoordinate, 0))
                break;

            if (grid[selectedYCoordinate][currentXCoordinate - 1] == MapParser.FLOOR) {
                for (int i = initialXCoordinate; i >= currentXCoordinate; i--) {
                    if (i == room.getTopLeftCoordinate().getX())
                        grid[selectedYCoordinate][i] = MapParser.DOOR;
                    else
                        grid[selectedYCoordinate][i] = MapParser.FLOOR;
                }

                break;
            }

            currentXCoordinate--;
        }
    }

    private void tryRightConnection(RoomStructure room, int selectedYCoordinate, int currentXCoordinate) {
        int initialXCoordinate = currentXCoordinate;

        while (canMakeConnection(currentXCoordinate, grid[0].length - 1)) {
            if (grid[selectedYCoordinate][currentXCoordinate + 1] == MapParser.FLOOR) {
                int topRightCoordinateX = room.getTopLeftCoordinate().getX() + room.getDimension().getWidth() - 1;

                for (int i = initialXCoordinate; i <= currentXCoordinate; i++)
                    grid[selectedYCoordinate][i] = (i == topRightCoordinateX) ? MapParser.DOOR : MapParser.FLOOR;

                break;
            }
            currentXCoordinate++;
        }
    }

    private boolean canMakeConnection(int coordinateInChosenAxis, int borderLimit) {
        return coordinateInChosenAxis != borderLimit;
    }

    private int selectVerticalIndex(RoomStructure room) {
        int startingPoint = room.getTopLeftCoordinate().getY() + 2;
        int finishPoint = room.getTopLeftCoordinate().getY() + room.getDimension().getHeight() - 2;

        return Randomizer.randInt(startingPoint, finishPoint - 1);
    }

    private void calculateRoomCenterCoordinates() {
        for (RoomStructure room : rooms) {
            Coordinate roomTopLeftCoordinates = room.getTopLeftCoordinate();
            int coordinateY = roomTopLeftCoordinates.getY() + (room.getDimension().getHeight() / 2);
            int coordinateX = roomTopLeftCoordinates.getX() + (room.getDimension().getWidth() / 2);

            roomsCoordinates.add(new Coordinate(coordinateX, coordinateY));
        }
    }
}
