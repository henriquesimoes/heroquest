package br.unicamp.ic.mc322.heroquest.map.generator.pathgenerator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.util.ArrayList;

public class PathGenerator {
    private char[][] grid;
    private ArrayList<RoomStructure> rooms;
    private ArrayList<Coordinate> roomsCoordinates = new ArrayList<>();

    public PathGenerator(char[][] grid, ArrayList<RoomStructure> rooms) {
        this.grid = grid;
        this.rooms = rooms;
    }

    public char[][] createPaths() {
        getCoordsOfTheCenterOfTheRooms();
        setPathsFromTheCenterOfTheRooms();
        return grid;
    }

    private void setPathsFromTheCenterOfTheRooms() {
        for (Coordinate roomCoords : roomsCoordinates) {
            digPath(roomCoords);
        }

        for (Coordinate roomCoords : roomsCoordinates) {
            refillInvalidPaths(roomCoords);
        }
    }

    private void digPath(Coordinate beginOfPath) {
        digInVertical(beginOfPath);
        digInHorizontal(beginOfPath);
    }

    private void refillInvalidPaths(Coordinate beginOfPath) {
        refillInVertical(beginOfPath);
        refillInHorizontal(beginOfPath);
    }

    private void refillInVertical(Coordinate beginOfPath) {
        refillInTopDirection(beginOfPath, beginOfPath.getY());
        refillInDownDirection(beginOfPath, beginOfPath.getY());
    }

    private void refillInTopDirection(Coordinate beginOfPath, int currentYCoord) {
        if (currentYCoord == 0)
            return;

        if (refillIfValidForTopDirection(beginOfPath, currentYCoord))
            return;

        refillInTopDirection(beginOfPath, currentYCoord - 1);

        refillIfValidForTopDirection(beginOfPath, currentYCoord);
    }

    private boolean refillIfValidForTopDirection(Coordinate beginOfPath, int currentYCoord) {
        if (grid[currentYCoord - 1][beginOfPath.getX()] == '#') {
            if (grid[currentYCoord][beginOfPath.getX() - 1] == '#' && grid[currentYCoord][beginOfPath.getX() + 1] == '#') {
                grid[currentYCoord][beginOfPath.getX()] = '#';

                return true;
            }

            if (grid[currentYCoord][beginOfPath.getX() - 1] == '/' && grid[currentYCoord][beginOfPath.getX() + 1] == '/') {
                grid[currentYCoord][beginOfPath.getX()] = '/';
                return true;
            }
        }


        return false;
    }

    private void refillInDownDirection(Coordinate beginOfPath, int currentYCoord) {
        if (currentYCoord == grid.length - 1)
            return;

        if (refillIfValidForDownDirection(beginOfPath, currentYCoord))
            return;


        refillInDownDirection(beginOfPath, currentYCoord + 1);

        refillIfValidForDownDirection(beginOfPath, currentYCoord);
    }

    private boolean refillIfValidForDownDirection(Coordinate beginOfPath, int currentYCoord) {
        if (grid[currentYCoord + 1][beginOfPath.getX()] == '#')
            if (grid[currentYCoord][beginOfPath.getX() - 1] == '#' && grid[currentYCoord][beginOfPath.getX() + 1] == '#') {

                grid[currentYCoord][beginOfPath.getX()] = '#';

                return true;
            }

        if (grid[currentYCoord][beginOfPath.getX() - 1] == '/' && grid[currentYCoord][beginOfPath.getX() + 1] == '/') {

            grid[currentYCoord][beginOfPath.getX()] = '/';

            return true;
        }

        return false;
    }

    private void refillInHorizontal(Coordinate beginOfPath) {
        refillInLeftDirection(beginOfPath, beginOfPath.getX());
    }

    private void refillInLeftDirection(Coordinate beginOfPath, int currentXCoord) {
        if (currentXCoord == 0)
            return;

        if (refillIfValidForLeftDirection(beginOfPath, beginOfPath.getX()))
            return;

        refillInLeftDirection(beginOfPath, currentXCoord - 1);

        refillIfValidForLeftDirection(beginOfPath, currentXCoord);
    }

    private boolean refillIfValidForLeftDirection(Coordinate beginOfPath, int currentXCoord) {
        if (grid[beginOfPath.getY()][currentXCoord - 1] == '#')
            if (grid[beginOfPath.getY() - 1][currentXCoord] == '#' && grid[beginOfPath.getY() + 1][currentXCoord] == '#') {
                grid[beginOfPath.getY()][currentXCoord] = '#';

                return true;
            }

        return false;
    }

    private void digInVertical(Coordinate beginOfPath) {
        int i;
        int wallsCounterToKnowWhenForIsOutOfTheRoom = 0;

        for (i = beginOfPath.getY(); i > 0; i--) {
            if (grid[i][beginOfPath.getX()] == '/') {
                wallsCounterToKnowWhenForIsOutOfTheRoom += 1;

                if (wallsCounterToKnowWhenForIsOutOfTheRoom > 1)
                    break;
            }
            grid[i][beginOfPath.getX()] = ' ';
        }

        wallsCounterToKnowWhenForIsOutOfTheRoom = 0;
        for (i = beginOfPath.getY(); i < grid.length - 1; i++) {
            if (grid[i][beginOfPath.getX()] == '/') {
                wallsCounterToKnowWhenForIsOutOfTheRoom += 1;

                if (wallsCounterToKnowWhenForIsOutOfTheRoom > 1)
                    break;
            }
            grid[i][beginOfPath.getX()] = ' ';
        }
    }

    private void digInHorizontal(Coordinate beginOfPath) {
        int i;
        int wallsCounterToKnowWhenForIsOutOfTheRoom = 0;

        for (i = beginOfPath.getX(); i > 0; i--) {
            if (grid[beginOfPath.getY()][i] == '/') {
                wallsCounterToKnowWhenForIsOutOfTheRoom += 1;

                if (wallsCounterToKnowWhenForIsOutOfTheRoom > 1)
                    break;
            }
            grid[beginOfPath.getY()][i] = ' ';
        }

        wallsCounterToKnowWhenForIsOutOfTheRoom = 0;
        for (i = beginOfPath.getX(); i < grid[0].length - 1; i++) {
            if (grid[beginOfPath.getY()][i] == '/') {
                wallsCounterToKnowWhenForIsOutOfTheRoom += 1;

                if (wallsCounterToKnowWhenForIsOutOfTheRoom > 1)
                    break;
            }
            grid[beginOfPath.getY()][i] = ' ';
        }
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