package br.unicamp.ic.mc322.heroquest.map.generator.pathgenerator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.random.Random;

import java.util.ArrayList;

public class PathGenerator {
    private char[][] grid;
    private Random rand = new Random();
    private ArrayList<RoomStructure> rooms;
    private ArrayList<GridContainer> gridSections;
    private ArrayList<Coordinate> roomsCoordinates = new ArrayList<>();

    public PathGenerator(char[][] grid, ArrayList<RoomStructure> rooms, ArrayList<GridContainer> gridSections) {
        this.grid = grid;
        this.rooms = rooms;
        this.gridSections = gridSections;
    }

    public void createPaths() {
        getCoordsOfTheCenterOfTheRooms();
        setCorridors();
        connectRoomsWithCorridors();
    }

    private void setCorridors() {
        for (GridContainer container : gridSections) {
            Coordinate topleft = container.getTopLeftCornerCoordinate();

            setVerticalCorridor(topleft, container);
            setHorizontalCorridor(topleft, container);
        }
    }

    private void setVerticalCorridor(Coordinate containerTopLeft, GridContainer container) {
        if (containerTopLeft.getX() + container.getDimensionX() >= grid[0].length)
            return;

        for (int i = 0; i < container.getDimensionY() + 2; i++) {
            if (containerTopLeft.getY() + i >= grid.length)
                break;

            if (containerTopLeft.getY() + i == 0 || containerTopLeft.getY() + i == grid.length - 1)
                continue;

            grid[containerTopLeft.getY() + i][containerTopLeft.getX() + container.getDimensionX()] = ' ';
            grid[containerTopLeft.getY() + i][containerTopLeft.getX() + container.getDimensionX() + 1] = ' ';
        }
    }

    private void setHorizontalCorridor(Coordinate containerTopLeft, GridContainer container) {
        if (containerTopLeft.getY() + container.getDimensionY() + 1 >= grid.length)
            return;

        for (int i = 0; i < container.getDimensionX() + 2; i++) {
            if (containerTopLeft.getX() + i >= grid[0].length)
                break;

            if (containerTopLeft.getX() + i == 0 || containerTopLeft.getX() + i == grid[0].length - 1)
                continue;

            grid[containerTopLeft.getY() + container.getDimensionY()][containerTopLeft.getX() + i] = ' ';
            grid[containerTopLeft.getY() + container.getDimensionY() + 1][containerTopLeft.getX() + i] = ' ';
        }
    }

    private void connectRoomsWithCorridors() {
        for (RoomStructure room : rooms) {
            tryCorridorConnection(room);
        }
    }

    private void tryCorridorConnection(RoomStructure room) {
        Coordinate roomTopLeftCoords = room.getRoomTopLeftCoordinates();
        int roomEndXCoordinate = roomTopLeftCoords.getX() + room.getRoomDimension().getWidth() - 1;

        tryLeftConnection(room, selectVerticalIndex(room), room.getRoomTopLeftCoordinates().getX());

        tryRightConnection(room, selectVerticalIndex(room), roomEndXCoordinate);

    }

    private void tryLeftConnection(RoomStructure room, int selectedYCoord, int currentXCoord) {
        if (currentXCoord <= 0)
            return;

        if (grid[selectedYCoord][currentXCoord - 1] == ' ') {
            if (currentXCoord == room.getRoomTopLeftCoordinates().getX())
                grid[selectedYCoord][currentXCoord] = 'D';
            else
                grid[selectedYCoord][currentXCoord] = ' ';

            return;
        }

        tryLeftConnection(room, selectedYCoord, currentXCoord - 1);

        if (grid[selectedYCoord][currentXCoord - 1] == ' ') {
            if (currentXCoord == room.getRoomTopLeftCoordinates().getX())
                grid[selectedYCoord][currentXCoord] = 'D';
            else
                grid[selectedYCoord][currentXCoord] = ' ';
        }
    }

    private void tryRightConnection(RoomStructure room, int selectedYCoord, int currentXCoord) {
        if (currentXCoord >= grid[0].length - 1)
            return;

        if (grid[selectedYCoord][currentXCoord + 1] == ' ') {
            if (currentXCoord == room.getRoomTopLeftCoordinates().getX() + room.getRoomDimension().getWidth() - 1)
                grid[selectedYCoord][currentXCoord] = 'D';
            else
                grid[selectedYCoord][currentXCoord] = ' ';

            return;
        }

        tryRightConnection(room, selectedYCoord, currentXCoord + 1);

        if (grid[selectedYCoord][currentXCoord + 1] == ' ') {
            if (currentXCoord == room.getRoomTopLeftCoordinates().getX() + room.getRoomDimension().getWidth() - 1)
                grid[selectedYCoord][currentXCoord] = 'D';
            else
                grid[selectedYCoord][currentXCoord] = ' ';

        }
    }

    private int selectVerticalIndex(RoomStructure room) {
        int startingPoint = room.getRoomTopLeftCoordinates().getY() + 2;
        int finishPoint = room.getRoomTopLeftCoordinates().getY() + room.getRoomDimension().getHeight() - 2;

        return rand.random(startingPoint, finishPoint);
    }

    private void getCoordsOfTheCenterOfTheRooms() {
        for (RoomStructure room : rooms) {
            Coordinate roomTopLeftCoordinates = room.getRoomTopLeftCoordinates();
            int coordY = roomTopLeftCoordinates.getY() + (room.getRoomDimension().getHeight() / 2);
            int coordX = roomTopLeftCoordinates.getX() + (room.getRoomDimension().getWidth() / 2);
            roomsCoordinates.add(new Coordinate(coordX, coordY));
        }
    }
}