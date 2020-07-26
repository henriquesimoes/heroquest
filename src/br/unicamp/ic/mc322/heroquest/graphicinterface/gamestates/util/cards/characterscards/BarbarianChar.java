package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;

public class BarbarianChar extends CharacterCard {
    private final static HeroKind HERO_KIND = HeroKind.BARBARIAN;
    private final static String DESCRIPTION = "A huge warrior, \nalways looking for \na good fight.";
    private final String SPRITESHEET_NAME = "barbarian_front.png";

    public BarbarianChar(Graphics2D graphics, GamePanel gamePanel) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, graphics, gamePanel);
        setImage(SPRITESHEET_NAME);
    }
}
