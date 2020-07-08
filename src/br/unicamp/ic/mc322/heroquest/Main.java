package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.terminal.TerminalEngine;

public class Main {

    public static void main(String[] args) {
        GameEngine engine = new TerminalEngine();

        engine.run();
    }
}
