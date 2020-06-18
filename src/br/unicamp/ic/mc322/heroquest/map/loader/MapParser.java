package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Wall;

public class MapParser {
    public static StructuralObject parse(char representation, Coordinate coordinate) {
        switch (representation) {
            case ' ':
                return new Floor(coordinate);
            case '#':
                return new Wall(coordinate);
            case 'D':
                return new Door(coordinate);
            default:
                throw new IllegalArgumentException("Invalid representation `" + representation + "`...");
        }
    }
}
