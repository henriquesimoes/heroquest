package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MapLoader {
    private Path base;

    // TODO: use application general configuration handler to get resources path.
    private static final String MAPS_PATH = "resources/maps";

    public MapLoader() {
        this.base = Paths.get(MAPS_PATH).toAbsolutePath();
    }

    public Map load(String filename) throws FileNotFoundException, CorruptedConfigurationFileException {
        File config = new File(base.resolve(filename).toUri());

        if (!config.exists())
            throw new FileNotFoundException();

        Scanner scanner = new Scanner(config);
        scanner.useDelimiter("\n");
        MapStructure mapStructure = readStructure(scanner);

        return new Map(mapStructure);
    }

    private MapStructure readStructure(Scanner scanner) throws CorruptedConfigurationFileException {
        MapStructure structure = new MapStructure();

        Coordinate origin = Coordinate.getOrigin();
        int dy = 0, width = 0;

        while (scanner.hasNext()) {
            String line = scanner.next();
            int size = line.length();

            if (width == 0)
                width = size;
            else if (size != width)
                throw new CorruptedConfigurationFileException("Number of columns is inconsistent...");

            for (int dx = 0; dx < width; dx++) {
                Coordinate coordinate = Coordinate.shift(origin, dx, dy);

                try {
                    StructuralObject obj = MapParser.parse(line.charAt(dx), coordinate);
                    structure.add(obj);
                } catch (IllegalArgumentException ex) {
                    throw new CorruptedConfigurationFileException(
                            String.format("Invalid char `%c` found on map configuration file", line.charAt(dx)));
                }
            }

            dy++;
        }

        return structure;
    }


}
