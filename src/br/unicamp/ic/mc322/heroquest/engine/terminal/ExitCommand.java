package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;

public class ExitCommand implements Command {
    private final IOInterface io;

    public ExitCommand(IOInterface io) {
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    public void execute() {
        io.showMessage("Exiting game...");
        System.exit(0);
    }
}
