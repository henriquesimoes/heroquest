package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

public class PlayCommand implements Command {
    private TerminalEngine engine;
    private IOInterface io;

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

        WalkerPlayer player = new WalkerPlayer(engine.getMap());
        Walker walker = new Wizard(player, name);

        engine.addPlayer(walker);

        engine.runLoop();
    }
}
