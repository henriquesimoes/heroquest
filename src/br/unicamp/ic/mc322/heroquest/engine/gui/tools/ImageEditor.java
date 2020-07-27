package br.unicamp.ic.mc322.heroquest.engine.gui.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEditor {
    public static BufferedImage scaleImage(BufferedImage image, int xScale, int yScale) {
        BufferedImage scaledImage = new BufferedImage(image.getWidth() * xScale, image.getHeight() * yScale,
                BufferedImage.TYPE_INT_ARGB);

        Graphics tempGraphics = scaledImage.createGraphics();
        tempGraphics.drawImage(image, 0, 0, scaledImage.getWidth(), scaledImage.getHeight(), null);

        return scaledImage;
    }
}
