package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class DwarfChar extends CharacterCard {
    private final static HeroesKind HERO_KIND = HeroesKind.DWARF;
    private final static String DESCRIPTION = "A powerful small guy";
    private final String SPRITESHEET_NAME = "dwarf_front.png";

    public DwarfChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
