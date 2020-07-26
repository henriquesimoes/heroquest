package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Handles the disk map loading process.
 */
public class MapLoader {
    private static final String MAPS_PATH = "resources/maps";
    private final Path base;
    private Collection<File> files;

    public MapLoader() {
        this.base = Paths.get(MAPS_PATH).toAbsolutePath();

        readAvailableMapFiles();
    }

    /**
     * Loads a file from the disk with the given name.
     *
     * @param filename - map file name, including extension
     * @return loaded map
     * @throws FileNotFoundException file does not exist on the resources folder
     */
    public Map load(String filename) throws FileNotFoundException {
        File file = getFile(filename);

        return load(file);
    }

    /**
     * @return all map file names available to be loaded
     */
    public String[] getMapNames() {
        Collection<String> names = new ArrayList<>();

        for (File file : files)
            names.add(file.getName());

        String[] result = new String[names.size()];
        names.toArray(result);

        return result;
    }

    private Map load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");

        return readMap(scanner);
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

    /**
     * Converts the map from the file to a concrete map object.
     *
     * @param scanner - wrapped file
     * @return file map
     */
    private Map readMap(Scanner scanner) {
        ArrayList<String> rows = new ArrayList<>();
        while (scanner.hasNext())
            rows.add(scanner.nextLine());

        char[][] matrix = new char[rows.size()][];
        for (int i = 0; i < rows.size(); i++)
            matrix[i] = rows.get(i).toCharArray();

        MapParser parser = new MapParser();
        MapBuilder builder = parser.parse(matrix);
        builder.buildMap();

        return builder.getResult();
    }
}
