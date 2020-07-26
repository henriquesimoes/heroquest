package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.engine.GameEngine;

public class GraphicEngine implements GameEngine {
    @Override
    public void run() {
        new GameWindow();
    }
}
