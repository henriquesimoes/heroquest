package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.util.ArrayList;

public interface Renderable {
    void render();

    ArrayList<Clickable> getClickableZones();
}
