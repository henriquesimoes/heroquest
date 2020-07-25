package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;

public class WizardChar extends CharacterCard {
    private final static HeroKind HERO_KIND = HeroKind.WIZARD;
    private final static String DESCRIPTION = "A powerful wizard \nwith powerful spells.";
    private final String SPRITESHEET_NAME = "wizard_front.png";

    public WizardChar(Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(HERO_KIND, HERO_KIND.toString(), DESCRIPTION, settings, graphics, screenStateManager);
        setImage(SPRITESHEET_NAME);
    }
}
