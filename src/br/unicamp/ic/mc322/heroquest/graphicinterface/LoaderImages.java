package br.unicamp.ic.mc322.heroquest.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoaderImages {

    // TODO: use application general configuration handler to get resources path.
    private static final String BASE_PATH = "resources/gameImages";
    private HashMap<Character, Image> images;

    public LoaderImages() {
        images = new HashMap<>();
        images.put('W', new ImageIcon(getFullName("wizzard_m_hit_anim_f0.png")).getImage());
        images.put('E', new ImageIcon(getFullName("elf_f_hit_anim_f0.png")).getImage());
        images.put('S', new ImageIcon(getFullName("skelet_idle_anim_f0.png")).getImage());
        images.put('Ŝ', new ImageIcon(getFullName("orc_shaman_idle_anim_f0.png")).getImage());
        images.put('G', new ImageIcon(getFullName("goblin_idle_anim_f0.png")).getImage());
        images.put('#', new ImageIcon(getFullName("wall_mid.png")).getImage());
        images.put('C', new ImageIcon(getFullName("chest_full_open_anim_f0.png")).getImage());
        images.put('c', new ImageIcon(getFullName("chest_empty_open_anim_f2.png")).getImage());
    }

    public HashMap<Character, Image> getImages() {
        return images;
    }

    private String getFullName(String filename) {
        return BASE_PATH + "/" + filename;
    }
}
