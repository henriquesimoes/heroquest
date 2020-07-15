package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;

public class MapParser {
    public static final char FLOOR = ' ';
    public static final char DOOR = 'D';
    public static final char WALL = '#';

    public MapBuilder parse(char[][] matrix) {
        MapBuilder builder = new MapBuilder();
        Dimension dimension = new Dimension(matrix[0].length, matrix.length);

        for (Coordinate coordinate : RegionSelector.getPlaneRegion(dimension)) {
            Coordinate relative = coordinate.toRelative();
            try {
                parseAndAdd(matrix[relative.getY()][relative.getX()], coordinate, builder);
            } catch (IndexOutOfBoundsException ex) {
                builder.add(new Wall(coordinate));
            }
        }

        builder.buildStructure();

        return builder;
    }

    private void parseAndAdd(char representation, Coordinate coordinate, MapBuilder builder) {
        switch (representation) {
            case FLOOR:
                builder.add(new Floor(coordinate));
                break;
            case WALL:
                builder.add(new Wall(coordinate));
                break;
            case DOOR:
                builder.add(new Door(coordinate));
                break;
            default:
                throw new CorruptedConfigurationFileException("Invalid representation `" + representation + "`...");
        }
    }
}
