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
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                findPathBetweenTwoRooms(roomsCoordinates.get(i), roomsCoordinates.get(j));
            }
        }
    }

    private void findPathBetweenTwoRooms(Coordinate room1Coords, Coordinate room2Coords) {
        int verticalDistance = room1Coords.getY() - room2Coords.getY();
        int horizontalDistance = room1Coords.getX() - room2Coords.getX();

        if (verticalDistance > 0) {
            if (horizontalDistance > 0) {
                digLeftUp(room1Coords, Math.abs(horizontalDistance), Math.abs(verticalDistance));
            }
        }
    }

    private void digLeftUp(Coordinate roomInUse, int horizontalDistance, int verticalDistance) {
        int i;
        for (i = 0; i < horizontalDistance; i++) {
            System.out.println("a");
            grid[roomInUse.getY()][roomInUse.getX() - i] = ' ';
        }
        for (i = 0; i < verticalDistance; i++) {
            grid[roomInUse.getY() - i][roomInUse.getY() - horizontalDistance] = ' ';
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