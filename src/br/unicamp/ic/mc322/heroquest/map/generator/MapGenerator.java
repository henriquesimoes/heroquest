package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.Room;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.HashMap;
import java.util.Random;

public class MapGenerator {
    private final int GRID_HEIGHT = 31;
    private final int GRID_WIDTH = 101;

    private final int ROOM_MIN_WIDTH = 9;
    private final int ROOM_MIN_HEIGHT = 5;
    private final int ROOM_MAX_WIDTH = 20;
    private final int ROOM_MAX_HEIGHT = 9;
    private String[][] grid;

    private Random randGenerator;
    private java.util.Map<String, Room> rooms;

    public MapGenerator(){
        rooms = new HashMap<>();
        randGenerator = new Random();
    }

    public void generate() {
        createGrid();
        print();
    }

    private void createGrid() {
        grid = new String[GRID_HEIGHT][GRID_WIDTH];

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[i][j] = "#";
            }
        }
    }

//    private void createRandomRooms() {
//        int numberOfRooms = randGenerator.nextInt(22 - 15) + 15;
//
//        for (int i = 0; i < numberOfRooms; i++) {
//
//        }
//    }
//
//
//    private void setRandomRoom() {
//        Coordinate coordinateOfTheCenterOfTheRoom = getRandCoordinateInGrid();
//        Pair<Integer, Integer> roomDimension = new Pair<>(randGenerator.nextInt(21) + 1, randGenerator.nextInt(ROOM_MAX_HEIGHT));
////            while(isAValidCoordinate())
//        }
//    }
//
//    private Coordinate getRandCoordinateInGrid() {
//        return (new Coordinate(randGenerator.nextInt(GRID_HEIGHT), randGenerator.nextInt(GRID_WIDTH)));
//    }

//    private boolean isAValidCoordinate(Coordinate coordinate) {
//        if (coordinatesOfTheCenterOfTheRooms.contains(coordinate))
//            return false;
//
//        for (Coordinate alreadySetted : coordinatesOfTheCenterOfTheRooms) {
//            if ()
//        }
//    }



    public void print() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.print('\n');
        }
    }
}
