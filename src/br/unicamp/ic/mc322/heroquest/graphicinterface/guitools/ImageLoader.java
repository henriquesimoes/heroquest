package br.unicamp.ic.mc322.heroquest.graphicinterface.guitools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    private static final String BASE_PATH = "resources/gameImages";

    public static BufferedImage readImage(String fileLocation) {
        String path = getFullName(fileLocation);
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private static String getFullName(String filename) {
        return BASE_PATH + "/" + filename;
    }
}