package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Heroes;

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
        String[] options = Heroes.getHeroesList();

        int choice = io.showOptionsAndGetAnswer(options, true);

        if (choice == 0)
            return;

        String name = io.getStringAnswer("What's your character name? ");

        /* It is needed to use `choice - 1` because the choice index starts in index 1, while
        *  the array of options starts in 0 */
        String selectedOption = options[choice - 1].toUpperCase();
        Heroes hero = Heroes.valueOf(selectedOption);

        Walker walker = hero.getHero(name, io);
        engine.addPlayer(walker);

        engine.runLoop();
    }
}
