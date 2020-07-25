package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class WizardChar extends CharacterCard {
    private final String SPRITESHEET_NAME = "wizard_front.png";
    private final static String NAME = "Wizard";
    private final static String DESCRIPTION = "A powerful wizard \nwith powerful spells.";

    public WizardChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HeroesKind.WIZARD, NAME, DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
