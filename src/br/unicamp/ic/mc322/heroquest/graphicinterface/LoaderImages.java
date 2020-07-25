package br.unicamp.ic.mc322.heroquest.graphicinterface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoaderImages {

    // TODO: use application general configuration handler to get resources path.
    private static final String BASE_PATH = "resources/gameImages";
    private HashMap<Character, ArrayList<BufferedImage>> images;

    public LoaderImages() {
        images = new HashMap<>();
        ArrayList<BufferedImage> temp = new ArrayList<>();
        temp.add(readImage(getFullName("wizzard_m_idle_anim_f0.png")));
        temp.add(readImage(getFullName("wizzard_m_idle_anim_f1.png")));
        temp.add(readImage(getFullName("wizzard_m_idle_anim_f2.png")));
        temp.add(readImage(getFullName("wizzard_m_idle_anim_f3.png")));

        images.put('W', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("elf_m_idle_anim_f0.png")));
        temp.add(readImage(getFullName("elf_m_idle_anim_f1.png")));
        temp.add(readImage(getFullName("elf_m_idle_anim_f2.png")));
        temp.add(readImage(getFullName("elf_m_idle_anim_f3.png")));
        images.put('E', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("skelet_idle_anim_f0.png")));
        temp.add(readImage(getFullName("skelet_idle_anim_f1.png")));
        temp.add(readImage(getFullName("skelet_idle_anim_f2.png")));
        temp.add(readImage(getFullName("skelet_idle_anim_f3.png")));
        images.put('S', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("orc_shaman_idle_anim_f0.png")));
        temp.add(readImage(getFullName("orc_shaman_idle_anim_f1.png")));
        temp.add(readImage(getFullName("orc_shaman_idle_anim_f2.png")));
        temp.add(readImage(getFullName("orc_shaman_idle_anim_f3.png")));
        images.put('Ŝ', temp);


        temp = new ArrayList<>();
        readImage(getFullName("goblin_idle_anim_f0.png"));
        readImage(getFullName("goblin_idle_anim_f1.png"));
        readImage(getFullName("goblin_idle_anim_f2.png"));
        readImage(getFullName("goblin_idle_anim_f3.png"));
        images.put('G', temp);


        temp = new ArrayList<>();
        temp.add(readImage(getFullName("wall_mid.png")));
        temp.add(readImage(getFullName("wall_mid.png")));
        temp.add(readImage(getFullName("wall_mid.png")));
        temp.add(readImage(getFullName("wall_mid.png")));
        images.put('#', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("chest_full_open_anim_f0.png")));
        temp.add(readImage(getFullName("chest_full_open_anim_f0.png")));
        temp.add(readImage(getFullName("chest_full_open_anim_f0.png")));
        temp.add(readImage(getFullName("chest_full_open_anim_f0.png")));
        images.put('C', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("chest_empty_open_anim_f2.png")));
        temp.add(readImage(getFullName("chest_empty_open_anim_f2.png")));
        temp.add(readImage(getFullName("chest_empty_open_anim_f2.png")));
        temp.add(readImage(getFullName("chest_empty_open_anim_f2.png")));
        images.put('c', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("floor_spikes_anim_f0.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f0.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f0.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f0.png")));
        images.put('t', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("floor_spikes_anim_f3.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f3.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f3.png")));
        temp.add(readImage(getFullName("floor_spikes_anim_f3.png")));
        images.put('T', temp);

        temp = new ArrayList<>();
        temp.add(readImage(getFullName("floor_1.png")));
        temp.add(readImage(getFullName("floor_1.png")));
        temp.add(readImage(getFullName("floor_1.png")));
        temp.add(readImage(getFullName("floor_1.png")));
        images.put(' ', temp);
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

    public HashMap<Character, ArrayList<BufferedImage>> getImages() {
        return images;
    }

    private String getFullName(String filename) {
        return BASE_PATH + "/" + filename;
    }
}
