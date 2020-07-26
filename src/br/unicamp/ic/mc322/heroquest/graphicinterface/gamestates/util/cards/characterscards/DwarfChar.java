package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;

public class DwarfChar extends CharacterCard {
    private final static HeroKind HERO_KIND = HeroKind.DWARF;
    private final static String DESCRIPTION = "A powerful small guy";
    private final String SPRITESHEET_NAME = "dwarf_front.png";

    public DwarfChar(Graphics2D graphics, GraphicEngine graphicEngine) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, graphics, graphicEngine);
        setImage(SPRITESHEET_NAME);
    }
}
