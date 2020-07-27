package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;

public class ElfChar extends CharacterCard {
    private final static HeroKind HERO_KIND = HeroKind.ELF;
    private final static String DESCRIPTION = "A mystical warrior\nthat can use spells \nand have big ears.";
    private final String SPRITESHEET_NAME = "elf_front_f0.png";

    public ElfChar(Graphics2D graphics, GamePanel gamePanel) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, graphics, gamePanel);
        setImage(SPRITESHEET_NAME);
    }
}
