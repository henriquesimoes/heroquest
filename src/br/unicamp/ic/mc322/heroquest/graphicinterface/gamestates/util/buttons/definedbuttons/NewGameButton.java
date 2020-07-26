package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.NewGameCommand;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class NewGameButton extends MenuButton {
    private static final String BUTTON_TEXT = "Play";

    public NewGameButton(Graphics2D graphics, GraphicEngine graphicEngine) {
        super(BUTTON_TEXT, graphics, graphicEngine);
        setCommand(new NewGameCommand(graphicEngine.getStateManager()));
    }
}
