package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;

import java.util.ArrayList;
import java.util.Collection;

public class MapParser {
    public static final char FLOOR = ' ';
    public static final char DOOR = 'D';
    public static final char WALL = '#';
    public static final char CHEST = 'C';

    public MapBuilder parse(char[][] matrix) {
        MapBuilder builder = new MapBuilder();
        Dimension dimension = new Dimension(matrix[0].length, matrix.length);

        Collection<FixedObject> fixedObjects = new ArrayList<>();

        for (Coordinate coordinate : RegionSelector.getPlaneRegion(dimension)) {
            Coordinate relative = coordinate.toRelative();
            char representation = matrix[relative.getY()][relative.getX()];

            try {
                parseAndAdd(representation, coordinate, builder);
            } catch (IndexOutOfBoundsException ex) {
                builder.add(new Wall(coordinate));
            } catch (IllegalStateException ex) {
                // Attempted to add a non-structural object...
                // Therefore, it must be a fixed object over the floor
                builder.add(new Floor(coordinate));
                fixedObjects.add(parseFixedObject(representation, coordinate));
            }
        }

        builder.buildStructure();

        for (FixedObject object : fixedObjects)
            builder.add(object);

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

    private FixedObject parseFixedObject(char representation, Coordinate coordinate) {
        switch (representation) {
            case CHEST:
                return new Chest(coordinate);
            default:
                throw new CorruptedConfigurationFileException(
                        "Invalid fixed object representation `" + representation + "`...");
        }
    }
}
