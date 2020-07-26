package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;

import java.util.ArrayList;

public interface StateViewer {
    void render();

    ArrayList<Clickable> getClickableZones();
}
