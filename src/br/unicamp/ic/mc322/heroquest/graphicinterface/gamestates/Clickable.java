package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import java.awt.geom.Rectangle2D;

public interface Clickable {
    Rectangle2D getBounds();
    void executeAction();
}
