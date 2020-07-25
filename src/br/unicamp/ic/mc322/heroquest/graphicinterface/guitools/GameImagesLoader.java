package br.unicamp.ic.mc322.heroquest.graphicinterface.guitools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameImagesLoader {

    private HashMap<Character, ArrayList<BufferedImage>> images;

    public GameImagesLoader() {
        images = new HashMap<>();
        ArrayList<BufferedImage> temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("wizzard_m_idle_anim_f0.png"));
        temp.add(ImageLoader.readImage("wizzard_m_idle_anim_f1.png"));
        temp.add(ImageLoader.readImage("wizzard_m_idle_anim_f2.png"));
        temp.add(ImageLoader.readImage("wizzard_m_idle_anim_f3.png"));

        images.put('W', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("elf_m_idle_anim_f0.png"));
        temp.add(ImageLoader.readImage("elf_m_idle_anim_f1.png"));
        temp.add(ImageLoader.readImage("elf_m_idle_anim_f2.png"));
        temp.add(ImageLoader.readImage("elf_m_idle_anim_f3.png"));
        images.put('E', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("skelet_idle_anim_f0.png"));
        temp.add(ImageLoader.readImage("skelet_idle_anim_f1.png"));
        temp.add(ImageLoader.readImage("skelet_idle_anim_f2.png"));
        temp.add(ImageLoader.readImage("skelet_idle_anim_f3.png"));
        images.put('S', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("orc_shaman_idle_anim_f0.png"));
        temp.add(ImageLoader.readImage("orc_shaman_idle_anim_f1.png"));
        temp.add(ImageLoader.readImage("orc_shaman_idle_anim_f2.png"));
        temp.add(ImageLoader.readImage("orc_shaman_idle_anim_f3.png"));
        images.put('Ŝ', temp);


        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("goblin_idle_anim_f0.png"));
        temp.add(ImageLoader.readImage("goblin_idle_anim_f1.png"));
        temp.add(ImageLoader.readImage("goblin_idle_anim_f2.png"));
        temp.add(ImageLoader.readImage("goblin_idle_anim_f3.png"));
        images.put('G', temp);


        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("wall_mid.png"));
        temp.add(ImageLoader.readImage("wall_mid.png"));
        temp.add(ImageLoader.readImage("wall_mid.png"));
        temp.add(ImageLoader.readImage("wall_mid.png"));
        images.put('#', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("chest_full_open_anim_f0.png"));
        temp.add(ImageLoader.readImage("chest_full_open_anim_f0.png"));
        temp.add(ImageLoader.readImage("chest_full_open_anim_f0.png"));
        temp.add(ImageLoader.readImage("chest_full_open_anim_f0.png"));
        images.put('C', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("chest_empty_open_anim_f2.png"));
        temp.add(ImageLoader.readImage("chest_empty_open_anim_f2.png"));
        temp.add(ImageLoader.readImage("chest_empty_open_anim_f2.png"));
        temp.add(ImageLoader.readImage("chest_empty_open_anim_f2.png"));
        images.put('c', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("floor_spikes_anim_f0.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f0.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f0.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f0.png"));
        images.put('t', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("floor_spikes_anim_f3.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f3.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f3.png"));
        temp.add(ImageLoader.readImage("floor_spikes_anim_f3.png"));
        images.put('T', temp);

        temp = new ArrayList<>();
        temp.add(ImageLoader.readImage("floor_1.png"));
        temp.add(ImageLoader.readImage("floor_1.png"));
        temp.add(ImageLoader.readImage("floor_1.png"));
        temp.add(ImageLoader.readImage("floor_1.png"));
        images.put(' ', temp);
    }

    public HashMap<Character, ArrayList<BufferedImage>> getImages() {
        return images;
    }

}
