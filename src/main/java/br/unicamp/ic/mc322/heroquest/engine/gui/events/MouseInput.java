package br.unicamp.ic.mc322.heroquest.engine.gui.events;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Coordinate previousCoords;
    private int mouseClickedXCoordinate;
    private int mouseClickedYCoordinate;

    public MouseInput() {
        previousCoords = new Coordinate(-1, -1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClickedXCoordinate = e.getX();
        mouseClickedYCoordinate = e.getY();
    }

    public Coordinate getMouseCoordsOnClick() {
        Coordinate coords = new Coordinate(mouseClickedXCoordinate, mouseClickedYCoordinate);

        if (coords.getX() == previousCoords.getX() && coords.getY() == previousCoords.getY()) {
            previousCoords = new Coordinate(-1, -1);
        } else
            previousCoords = coords;

        return coords;
    }

    public void clear() {
        previousCoords = new Coordinate(-1, -1);
        mouseClickedXCoordinate = -1;
        mouseClickedYCoordinate = -1;
    }

}
