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
    }

    private void digPath(Coordinate beginOfPath) {
        digInVertical(beginOfPath);
        digInHorizontal(beginOfPath);
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
        for (i = beginOfPath.getY(); i < grid.length; i++) {
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
        for (i = beginOfPath.getX(); i < grid[0].length; i++) {
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