package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Wall;

public class MapParser {
    public static void parseAndAdd(char representation, Coordinate coordinate, MapBuilder builder) {
        switch (representation) {
            case ' ':
                builder.add(new Floor(coordinate));
                break;
            case '#':
                builder.add(new Wall(coordinate));
                break;
            case 'D':
                builder.add(new Door(coordinate));
                break;
            default:
                throw new IllegalArgumentException("Invalid representation `" + representation + "`...");
        }
    }
}
