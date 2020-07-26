package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.engine.GameEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;

public class Main {

    public static void main(String[] args) {
        /*
         * In order to execute the graphic interface engine, change the
         * `TerminalEngine` to a `GraphicEngine`.
         */
        GameEngine engine = new GraphicEngine();

        /*
         * Starts the configured game engine.
         */
        engine.run();
    }
}
