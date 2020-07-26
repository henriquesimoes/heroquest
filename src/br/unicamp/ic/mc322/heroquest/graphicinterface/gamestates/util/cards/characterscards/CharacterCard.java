package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.ImageEditor;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.ImageLoader;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterCard extends Card {
    private HeroKind heroKind;
    private GraphicEngine graphicEngine;

    public CharacterCard(HeroKind heroKind, String name, String description, Graphics2D graphics, GraphicEngine graphicEngine) {
        super(name, description, graphics);
        this.graphicEngine = graphicEngine;
        this.heroKind = heroKind;
    }

    protected void setImage(String spriteSheetName) {
        BufferedImage image = ImageLoader.readImage(spriteSheetName);
        setImageContent(ImageEditor.scaleImage(image, 2, 2));
    }

    @Override
    public States executeAction() {
        graphicEngine.setHeroKid(heroKind);
        return States.GAME_RUNNING;
    }
}
