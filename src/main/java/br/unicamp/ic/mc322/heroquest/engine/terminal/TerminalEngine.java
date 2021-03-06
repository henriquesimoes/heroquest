package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.*;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.MapPopulator;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class TerminalEngine implements GameEngine {
    private final TerminalIO io;
    private final MapManager manager;
    private Map map;
    private GameLevel level;

    public TerminalEngine() {
        manager = new MapManager();
        io = new TerminalIO();

        map = manager.generate();
        level = GameLevel.EASY;
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

    protected void runLoop() {
        GameMonitor monitor = GameMonitor.getInstance();

        MapPopulator populator = new MapPopulator(level);
        map = populator.populate(map);

        monitor.subscribe(map);
        new GameLoop(map).run();

        monitor.unsubscribe(map);
        map = manager.generate();
    }

    private void showStartUpMessages() {
        io.showMessage(" Welcome to HeroQuest!");
        io.showMessage(" ======== *** ========");
        io.showSpan();
    }
}
