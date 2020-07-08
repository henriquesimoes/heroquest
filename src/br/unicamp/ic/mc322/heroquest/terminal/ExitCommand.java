package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

public class ExitCommand implements Command {
    private IOInterface io;

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
