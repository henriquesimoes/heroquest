package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.RandomMapGeneratorCommand;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";
    private GraphicEngine graphicEngine;

    public RandomMapButton(Graphics2D graphics, GraphicEngine graphicEngine) {
        super(BUTTON_TEXT, graphics, graphicEngine);
        this.graphicEngine = graphicEngine;

        setCommand(new RandomMapGeneratorCommand(graphicEngine));
    }
}
