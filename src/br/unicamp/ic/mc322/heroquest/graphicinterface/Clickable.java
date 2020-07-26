package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;

import java.awt.geom.Rectangle2D;

public interface Clickable {
    Rectangle2D getBounds();

    State executeAction();
}
