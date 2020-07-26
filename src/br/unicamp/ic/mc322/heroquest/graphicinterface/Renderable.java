package br.unicamp.ic.mc322.heroquest.graphicinterface;

import java.util.ArrayList;

public interface Renderable {
    void render();

    ArrayList<Clickable> getClickableZones();
}
