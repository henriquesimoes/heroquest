package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager;

import java.util.ArrayList;

public interface Renderable {
    void render();

    ArrayList<Clickable> getClickableZones();
}
