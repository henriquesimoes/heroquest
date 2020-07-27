package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.cards.characterscards;

import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands.ChooseCharacterCommand;
import br.unicamp.ic.mc322.heroquest.engine.gui.tools.ImageEditor;
import br.unicamp.ic.mc322.heroquest.engine.gui.tools.ImageLoader;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterCard extends Card {

    public CharacterCard(HeroKind heroKind, String name, String description, Graphics2D graphics, GamePanel gamePanel) {
        super(name, description, graphics);
        setCommand(new ChooseCharacterCommand(heroKind, gamePanel));
    }

    protected void setImage(String spriteSheetName) {
        BufferedImage image = ImageLoader.readImage(spriteSheetName);
        setImageContent(ImageEditor.scaleImage(image, 2, 2));
    }

    @Override
    public void executeAction() {
        executeCommand();
    }
}
