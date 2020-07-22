package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.GameEngine;
import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.engine.GameLoop;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class TerminalEngine implements GameEngine {
    private TerminalIO io;
    private Map map;
    private GameLevel level;

    public TerminalEngine() {
        level = GameLevel.EASY;

        MapManager manager = new MapManager(level);
        map = manager.generate();

        io = new TerminalIO();
    }

    @Override
    public void run() {
        Command[] commands = {
                new ChooseLevelCommand(this, io),
                new GenerateMapCommand(this, io),
                new ChooseMapCommand(this, io),
                new PlayCommand(this, io),
                new ExitCommand(io)
        };

        showStartUpMessages();

        while (true) {
            io.showMessage("Game Menu");
            io.selectAndExecute(commands, false);
            io.showSpan();
        }
    }

    protected void setMap(Map map) {
        this.map = map;
    }

    protected void addPlayer(Walker walker) {
        map.add(walker);
    }

    protected void setLevel(GameLevel level) {
        this.level = level;
    }

    protected GameLevel getLevel() {
        return level;
    }

    protected void runLoop() {
        new GameLoop(map).run();
    }

    private void showStartUpMessages() {
        io.showMessage(" Welcome to HeroQuest!");
        io.showMessage(" ======== *** ========");
        io.showSpan();
    }
}
