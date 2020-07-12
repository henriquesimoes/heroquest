package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

class RoomGenerator {
    private int minimumWidth;
    private int minimumHeight;
    ArrayList<GridContainer> gridSections;

    public RoomGenerator(ArrayList<GridContainer> gridSections, int minimumWidth, int minimumHeight) {
        this.gridSections = gridSections;
        this.minimumWidth = minimumWidth;
        this.minimumHeight = minimumHeight;
    }

    public ArrayList<RoomStructure> createRandomRooms() {
        ArrayList<RoomStructure> rooms = new ArrayList<>();

        for (GridContainer container : gridSections) {
            Dimension dimensions = getRandomRoomDimension(container);
            Coordinate roomCoordinates = getRandomRoomCoordinates(container, dimensions);

            rooms.add(new RoomStructure(dimensions, roomCoordinates));
        }

        return rooms;
    }

    private Dimension getRandomRoomDimension(GridContainer container) {
        int width = Math.max(minimumWidth, Randomizer.nextInt(container.getWidth()) - 2);
        int height = Math.max(minimumHeight, Randomizer.nextInt(container.getHeight()) - 2);

        return new Dimension(width, height);
    }

    private Coordinate getRandomRoomCoordinates(GridContainer container, Dimension dimensions) {
        Coordinate topLeftCoordinate = container.getTopLeftCoordinate();

        int coordinateY = Randomizer.nextInt(container.getHeight() - dimensions.getHeight() + 1)
                + topLeftCoordinate.getY();
        int coordinateX = Randomizer.nextInt(container.getWidth() - dimensions.getWidth() + 1)
                + topLeftCoordinate.getX();

        return new Coordinate(coordinateX, coordinateY);
    }
}
