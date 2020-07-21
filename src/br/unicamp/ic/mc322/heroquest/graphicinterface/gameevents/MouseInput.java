package br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenState;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private int mouseClickedXCoordinate = 0;
    private int mouseClickedYCoordinate = 0;

    public MouseInput(GamePanel game) {
        game.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClickedXCoordinate = e.getX();
        mouseClickedYCoordinate = e.getY();

        GameWindow.setScreenState(ScreenState.MAP_SELECTION);
    }

    public Coordinate getMouseCoordsOnClick() {
        return new Coordinate(mouseClickedXCoordinate, mouseClickedYCoordinate);
    }

}
