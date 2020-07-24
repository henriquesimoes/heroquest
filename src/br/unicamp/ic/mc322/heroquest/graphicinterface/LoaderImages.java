package br.unicamp.ic.mc322.heroquest.graphicinterface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LoaderImages {

    // TODO: use application general configuration handler to get resources path.
    private static final String BASE_PATH = "resources/gameImages";
    private HashMap<Character, Image> images;

    public LoaderImages() {
        images = new HashMap<>();
        images.put('W', readImage(getFullName("wizzard_m_hit_anim_f0.png")));
        images.put('E', readImage(getFullName("elf_f_hit_anim_f0.png")));
        images.put('S', readImage(getFullName("skelet_idle_anim_f0.png")));
        images.put('Ŝ', readImage(getFullName("orc_shaman_idle_anim_f0.png")));
        images.put('G', readImage(getFullName("goblin_idle_anim_f0.png")));
        images.put('#', readImage(getFullName("wall_mid.png")));
        images.put('C', readImage(getFullName("chest_full_open_anim_f0.png")));
        images.put('c', readImage(getFullName("chest_empty_open_anim_f2.png")));
        images.put('t', readImage(getFullName("floor_spikes_anim_f0.png")));
        images.put('T', readImage(getFullName("floor_spikes_anim_f3.png")));
        images.put(' ', readImage(getFullName("floor_1.png")));

    }

    public static BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public HashMap<Character, Image> getImages() {
        return images;
    }

    private String getFullName(String filename) {
        return BASE_PATH + "/" + filename;
    }
}
