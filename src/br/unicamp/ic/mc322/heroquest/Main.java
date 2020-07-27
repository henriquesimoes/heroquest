package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.engine.GameEngine;
import br.unicamp.ic.mc322.heroquest.engine.gui.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.engine.terminal.TerminalEngine;
import br.unicamp.ic.mc322.heroquest.map.loader.InvalidResourcesFolderLocationException;

public class Main {

    public static void main(String[] args) {
        try {
            /*
             * In order to execute the graphic interface engine, change the
             * `TerminalEngine` to a `GraphicEngine`.
             */
            GameEngine engine = new TerminalEngine();

            /*
             * Starts the configured game engine.
             */
            engine.run();
        } catch (InvalidResourcesFolderLocationException ex) {
            System.err.println("Resources configuration folder should be place on the project root folder, i.e. " +
                    "side by side with the `/br` folder...");
        }
    }
}
