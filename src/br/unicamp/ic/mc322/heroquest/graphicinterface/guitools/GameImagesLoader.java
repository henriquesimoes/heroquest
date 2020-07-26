package br.unicamp.ic.mc322.heroquest.graphicinterface.guitools;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning.RenderableObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameImagesLoader {
    private HashMap<RenderableObject, ArrayList<BufferedImage>> images;

    public GameImagesLoader() {
        images = new HashMap<>();

        ArrayList<BufferedImage> temp;

        for (RenderableObject object : RenderableObject.values()) {
            String name = object.getImageName();
            int size = object.getImageFrames();

            temp = new ArrayList<>();

            for (int i = 0; i < size; i++)
                temp.add(ImageLoader.readImage(name + i + ".png"));

            images.put(object, temp);
        }

    }

    public HashMap<RenderableObject, ArrayList<BufferedImage>> getImages() {
        return images;
    }
}
