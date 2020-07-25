package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager;

import java.awt.geom.Rectangle2D;

public interface Clickable {
    Rectangle2D getBounds();

    void executeAction();
}
