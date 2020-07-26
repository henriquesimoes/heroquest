package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.BackToPrevStateCommand;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BackButton extends MenuButton {
    private static final String BUTTON_TEXT = "BACK";

    public BackButton(Graphics2D graphics, GraphicEngine graphicEngine) {
        super(BUTTON_TEXT, graphics, graphicEngine);

        setCommand(new BackToPrevStateCommand(graphicEngine.getStateManager()));
    }
}
