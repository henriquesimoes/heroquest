package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

public class ChooseLevelCommand implements Command {
    private final TerminalEngine engine;
    private final IOInterface io;

    public ChooseLevelCommand(TerminalEngine engine, IOInterface io) {
        this.engine = engine;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Set level";
    }

    @Override
    public void execute() {
        GameLevel[] levels = GameLevel.values();
        String[] options = new String[levels.length];

        for (int i = 0; i < levels.length; i++)
            options[i] = levels[i].toString();

        io.showMessage("Choose a level:");
        int answer = io.showOptionsAndGetAnswer(options, true) - 1;

        if (answer != -1)
            engine.setLevel(levels[answer]);
    }
}
