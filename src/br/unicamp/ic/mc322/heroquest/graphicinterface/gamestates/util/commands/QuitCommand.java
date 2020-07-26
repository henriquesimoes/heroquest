package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;

public class QuitCommand implements Command {

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        System.exit(1);
    }
}
