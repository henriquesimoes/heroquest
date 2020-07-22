package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;

public class PlayCommand implements Command {
    private final TerminalEngine engine;
    private final IOInterface io;

    public PlayCommand(TerminalEngine engine, IOInterface io) {
        this.engine = engine;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Play";
    }

    @Override
    public void execute() {
        io.showMessage("Choose your player: ");
        String[] options = {
                "Wizard"
        };

        int choice = io.showOptionsAndGetAnswer(options, true);

        if (choice == 0)
            return;

        String name = io.getStringAnswer("What's your character name? ");

        Walker walker = new Wizard(name, io);

        engine.addPlayer(walker);

        engine.runLoop();
    }
}
