package br.unicamp.ic.mc322.heroquest.graphicinterface.guitools;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameImagesLoader {

    private HashMap<Character, ArrayList<BufferedImage>> images;

    public GameImagesLoader() {
        images = new HashMap<>();
        HashMap<Character, Pair<String, Integer>> nameSize = new HashMap<>();

        nameSize.put('W', new Pair<>("wizard_f", 4));
        nameSize.put('E', new Pair<>("elf_f", 4));
        nameSize.put('B', new Pair<>("knight_f", 4));
        nameSize.put('F', new Pair<>("dwarf_f", 4));
        nameSize.put('S', new Pair<>("skeleton_f", 4));
        nameSize.put('Ŝ', new Pair<>("skeleton_wizard_f", 4));
        nameSize.put('G', new Pair<>("goblin_f", 4));
        nameSize.put('#', new Pair<>("wall_f", 1));
        nameSize.put('D', new Pair<>("door_f", 1));
        nameSize.put('C', new Pair<>("chest_close_f", 1));
        nameSize.put('c', new Pair<>("chest_open_f", 1));
        nameSize.put('T', new Pair<>("trap_armed_f", 1));
        nameSize.put('t', new Pair<>("trap_unarmed_f", 1));
        nameSize.put(' ', new Pair<>("floor_f", 1));

        ArrayList<BufferedImage> temp;

        for (Map.Entry<Character, Pair<String, Integer>> entry : nameSize.entrySet()) {
            Character character = entry.getKey();
            String name = entry.getValue().getFirst();
            int size = entry.getValue().getSecond();

            temp = new ArrayList<>();

            for (int i = 0; i < size; i++)
                temp.add(ImageLoader.readImage(name + i + ".png"));

            images.put(character, temp);
        }

    }

    public HashMap<Character, ArrayList<BufferedImage>> getImages() {
        return images;
    }

}
