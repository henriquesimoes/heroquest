package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines the map object representation to be used on the map generation and
 * on the maps stored on the disk. Moreover, it is allows parsing a matrix of
 * characters into a Map object.
 */
public class MapParser {
    public static final char FLOOR = ' ';
    public static final char DOOR = 'D';
    public static final char WALL = '#';
    public static final char CHEST = 'C';
    public static final char SECRET_DOOR = 'P';

    /**
     * Parses the given matrix to a map. The chars used must follow the constant
     * values defined in this class.
     *
     * @param matrix map matrix
     * @return map object
     */
    public MapBuilder parse(char[][] matrix) {
        MapBuilder builder = new MapBuilder();
        Dimension dimension = new Dimension(matrix[0].length, matrix.length);

        Collection<FixedObject> fixedObjects = new ArrayList<>();

        for (Coordinate coordinate : RegionSelector.getPlaneRegion(dimension)) {
            Coordinate relative = coordinate.toRelative();
            char representation = matrix[relative.getY()][relative.getX()];

            try {
                builder.add(parseStructuralObject(representation, coordinate));
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

    private StructuralObject parseStructuralObject(char representation, Coordinate coordinate) {
        switch (representation) {
            case FLOOR:
                return new Floor(coordinate);
            case WALL:
                return new Wall(coordinate);
            case DOOR:
                return new Door(coordinate);
            case SECRET_DOOR:
                return new SecretDoor(coordinate);
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
