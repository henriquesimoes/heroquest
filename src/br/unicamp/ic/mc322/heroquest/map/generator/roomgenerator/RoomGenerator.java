package br.unicamp.ic.mc322.heroquest.map.generator.roomgenerator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.util.ArrayList;
import java.util.Random;

public class RoomGenerator {
    private int roomMinWidth;
    private int roomMinHeight;
    Random random = new Random();
    ArrayList<GridContainer> gridSections;
    private ArrayList<RoomStructure> rooms = new ArrayList<>();

    public RoomGenerator(ArrayList<GridContainer> gridSections, int roomMinWidth, int roomMinHeight) {
        this.gridSections = gridSections;
        this.roomMinWidth = roomMinWidth;
        this.roomMinHeight = roomMinHeight;
    }

    public ArrayList<RoomStructure> createRandomRooms() {
        generateRooms();
        return rooms;
    }

    private void generateRooms() {
        for (GridContainer container : gridSections) {
            Dimension dimensions = getRandomRoomDimensions(container);
            Coordinate roomCoordinates = getRandomRoomCoordinates(container, dimensions);
            Coordinate doorCoordinates = new Coordinate(0, 0);
            rooms.add(new RoomStructure(dimensions, roomCoordinates, doorCoordinates));
        }
    }

    private Dimension getRandomRoomDimensions(GridContainer container) {
        int dimensionX = Math.max(roomMinWidth, random.nextInt(container.getDimensionX()) - 2);
        int dimensionY = Math.max(roomMinHeight, random.nextInt(container.getDimensionY()) - 2);

        return (new Dimension(dimensionX, dimensionY));
    }

    private Coordinate getRandomRoomCoordinates(GridContainer container, Dimension dimensions) {
        Coordinate containerCoordinates = container.getTopLeftCornerCoordinate();

        int coordY = random.nextInt(container.getDimensionY() - dimensions.getHeight() + 1) + containerCoordinates.getY();
        int coordX = random.nextInt(container.getDimensionX() - dimensions.getWidth() + 1) + containerCoordinates.getX();

        return (new Coordinate(coordX, coordY));
    }
}
