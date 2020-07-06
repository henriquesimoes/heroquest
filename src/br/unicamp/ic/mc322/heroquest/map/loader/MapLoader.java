package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.core.SinglePlacement;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class MapLoader {
    private Path base;

    // TODO: use application general configuration handler to get resources path.
    private static final String MAPS_PATH = "resources/maps";
    private Collection<File> files;

    public MapLoader() {
        this.base = Paths.get(MAPS_PATH).toAbsolutePath();

        readAvailableMapFiles();
    }

    public MapBuilder load(String filename) throws FileNotFoundException {
        File file = getFile(filename);

        return load(file);
    }

    public String[] getMapNames() {
        Collection<String> names = new ArrayList<>();

        for (File file : files)
            names.add(file.getName());

        String[] result = new String[names.size()];
        names.toArray(result);

        return result;
    }

    private MapBuilder load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");
        MapBuilder builder = new MapBuilder(new SinglePlacement());

        readStructure(scanner, builder);

        return builder;
    }

    private void readAvailableMapFiles() {
        File folder = new File(base.toUri());
        files = new ArrayList<>();

        Collections.addAll(files, folder.listFiles());
    }

    private File getFile(String filename) throws FileNotFoundException {
        File file = new File(base.resolve(filename).toUri());

        if (!file.exists())
            throw new FileNotFoundException();

        return file;
    }

    private void readStructure(Scanner scanner, MapBuilder builder) {
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
;                    MapParser.parseAndAdd(line.charAt(dx), coordinate, builder);
                } catch (IllegalArgumentException ex) {
                    throw new CorruptedConfigurationFileException(
                            String.format("Invalid char `%c` found on map configuration file", line.charAt(dx)));
                }
            }

            dy++;
        }

        builder.buildStructure();
    }
}
