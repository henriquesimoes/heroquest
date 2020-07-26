package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.ImageEditor;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.ImageLoader;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterCard extends Card {
    private HeroKind heroKind;
    private GamePanel gamePanel;

    public CharacterCard(HeroKind heroKind, String name, String description, Graphics2D graphics, GamePanel gamePanel) {
        super(name, description, graphics);
        this.gamePanel = gamePanel;
        this.heroKind = heroKind;
    }

    protected void setImage(String spriteSheetName) {
        BufferedImage image = ImageLoader.readImage(spriteSheetName);
        setImageContent(ImageEditor.scaleImage(image, 2, 2));
    }

    @Override
    public States executeAction() {
        gamePanel.setHeroKind(heroKind);
        return States.GAME_RUNNING;
    }
}
