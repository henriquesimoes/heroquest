package br.unicamp.ic.mc322.heroquest.engine.gui.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class ImageLoader {
    private static final String BASE_PATH = "resources/gameImages";

    public static BufferedImage readImage(String filename) {
        String resourcePath = Paths.get(BASE_PATH).resolve(filename).toString();
        BufferedImage img = null;

        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);

            img = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}