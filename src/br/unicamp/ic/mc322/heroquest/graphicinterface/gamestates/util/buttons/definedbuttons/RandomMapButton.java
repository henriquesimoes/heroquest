package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
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
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public States executeAction() {
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        graphicEngine.setMap(new MapManager().generate());
        graphicEngine.setName(nickName);
        return States.CHOOSE_CHARACTER;
    }
}
