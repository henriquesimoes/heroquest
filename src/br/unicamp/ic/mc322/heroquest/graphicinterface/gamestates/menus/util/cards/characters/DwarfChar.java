package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class DwarfChar extends CharacterCard {
    private final String SPRITESHEET_NAME = "dwarf_front.png";
    private final static String NAME = "Dwarf";
    private final static String DESCRIPTION = "A powerful small guy";

    public DwarfChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HeroesKind.DWARF, NAME, DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
