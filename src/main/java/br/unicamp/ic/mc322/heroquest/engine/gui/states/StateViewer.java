package br.unicamp.ic.mc322.heroquest.engine.gui.states;

import br.unicamp.ic.mc322.heroquest.engine.gui.Clickable;

import java.util.ArrayList;

public interface StateViewer {
    void render();

    ArrayList<Clickable> getClickableZones();
}
