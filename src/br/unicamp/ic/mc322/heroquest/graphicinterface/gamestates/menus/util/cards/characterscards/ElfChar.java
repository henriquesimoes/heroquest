package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class ElfChar extends CharacterCard {
    private final static HeroesKind HERO_KIND = HeroesKind.ELF;
    private final static String DESCRIPTION = "A mystical warrior\nthat can use spells \nand have big ears.";
    private final String SPRITESHEET_NAME = "elf_front.png";

    public ElfChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
