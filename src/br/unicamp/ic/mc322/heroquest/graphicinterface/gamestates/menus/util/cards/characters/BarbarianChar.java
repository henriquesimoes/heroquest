package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class BarbarianChar extends CharacterCard {
    private final String SPRITESHEET_NAME = "barbarian_front.png";
    private final static String NAME = "Barbarian";
    private final static String DESCRIPTION = "A huge warrior, \nalways looking for \na good fight.";

    public BarbarianChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HeroesKind.BARBARIAN, NAME, DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
