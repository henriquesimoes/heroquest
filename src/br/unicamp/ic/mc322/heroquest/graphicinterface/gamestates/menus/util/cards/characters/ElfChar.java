package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class ElfChar extends CharacterCard {
    private final String SPRITESHEET_NAME = "elf_front.png";
    private final static String NAME = "Elf";
    private final static String DESCRIPTION = "A mystical warrior\nthat can use spells \nand have big ears.";

    public ElfChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HeroesKind.ELF, NAME, DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
