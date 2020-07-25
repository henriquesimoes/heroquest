package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.ImageEditor;
import br.unicamp.ic.mc322.heroquest.graphicinterface.ImageLoader;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterCard extends Card {
    private HeroesKind heroKind;
    private final Settings SETTINGS;
    private ScreenStateManager screenStateManager;

    public CharacterCard(HeroesKind heroKind, String name, String description, Settings settings, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(name, description, graphics);
        this.screenStateManager = screenStateManager;
        this.SETTINGS = settings;
        this.heroKind = heroKind;
    }

    protected void setImage(String spriteSheetName){
         BufferedImage image = ImageLoader.readImage(spriteSheetName);
         setImageContent(ImageEditor.scaleImage(image, 2, 2));
    }

    @Override
    public void executeAction() {
        SETTINGS.setWalker(heroKind);
        screenStateManager.setState(ScreenStates.GAME_RUNNING);
    }
}
