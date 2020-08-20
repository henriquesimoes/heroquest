package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Handles the disk map loading process.
 */
public class MapLoader {
    private static final String MAPS_PATH = "maps";
    private Collection<String> filenames;

    public MapLoader() {
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
        String resourcePath = Paths.get(MAPS_PATH).resolve(filename).toString();

        // The map must be read as a stream, since jar files does not contain files
        // (at least not as a filesystem does). ClassLoader can abstract this and handle
        // both of them.
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);

        if (stream == null)
            throw new FileNotFoundException();

        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\n");

        return readMap(scanner);
    }

    /**
     * @return all map file names available to be loaded
     */
    public String[] getMapNames() {
        Collection<String> names = new ArrayList<>();

        for (String file : filenames)
            names.add(new File(file).getName());

        return names.toArray(new String[0]);
    }

    private void readAvailableMapFiles() {
        File projectFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        filenames = new ArrayList<>();

        if (projectFile.isDirectory()) {
            /*
             * Running on a non-jar environment. Thus, it is possible to list files within the
             * resource folder directly.
             */
            File folder = new File(projectFile.toPath().resolve(MAPS_PATH).toUri());
            for (File file : folder.listFiles())
                filenames.add(file.getPath());
        } else {
            /*
             * Jar files does not contain files neither folders, but entries. Thus, it is needed
             * to fetch the map file names differently.
             */
            try {
                final Enumeration<JarEntry> entries = new JarFile(projectFile).entries();

                while (entries.hasMoreElements()) {
                    String name = entries.nextElement().getName();
                    if (name.startsWith(MAPS_PATH + '/') && name.endsWith(".map"))
                        filenames.add(name);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
