package br.unicamp.ic.mc322.heroquest.engine.gui.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageLoader {
    private static final String BASE_PATH = "resources/gameImages";

    public static BufferedImage readImage(String fileLocation) {
        Path path = getFullName(fileLocation);
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(path.toUri()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private static Path getFullName(String filename) {
        Path folder = Paths.get(ClassLoader.getSystemClassLoader().getResource(BASE_PATH).getPath());

        return folder.resolve(filename);
    }
}