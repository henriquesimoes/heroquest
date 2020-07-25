package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class BarbarianChar extends CharacterCard {
    private final static HeroesKind HERO_KIND = HeroesKind.BARBARIAN;
    private final static String DESCRIPTION = "A huge warrior, \nalways looking for \na good fight.";
    private final String SPRITESHEET_NAME = "barbarian_front.png";

    public BarbarianChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
