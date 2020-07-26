package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

public class PlayCommand implements Command {
    private final TerminalEngine engine;
    private final TerminalIO io;

    public PlayCommand(TerminalEngine engine, TerminalIO io) {
        this.engine = engine;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Play";
    }

    @Override
    public void execute() {
        HeroKind[] heroKinds = HeroKind.values();
        String[] options = new String[heroKinds.length];

        for (int i = 0; i < heroKinds.length; i++)
            options[i] = heroKinds[i].toString();

        io.showMessage("Choose your player: ");
        int choice = io.showOptionsAndGetAnswer(options, true);

        if (choice == 0)
            return;

        String name = io.getStringAnswer("What's your character's name? ");

        /*
         * It is needed to use `choice - 1` because the choice index starts in index 1, while
         * the array of options starts in 0
         */
        Walker walker = heroKinds[choice - 1].getHero(name, io);

        engine.addPlayer(walker);

        engine.runLoop();
    }
}
