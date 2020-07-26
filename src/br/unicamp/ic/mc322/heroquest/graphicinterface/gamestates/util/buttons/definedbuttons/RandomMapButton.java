package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.GenerateRandomMapCommand;

import java.awt.*;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";
    private GraphicEngine graphicEngine;

    public RandomMapButton(Graphics2D graphics, GraphicEngine graphicEngine) {
        super(BUTTON_TEXT, graphics, graphicEngine);
        this.graphicEngine = graphicEngine;

        setCommand(new GenerateRandomMapCommand(graphicEngine));
    }
}
