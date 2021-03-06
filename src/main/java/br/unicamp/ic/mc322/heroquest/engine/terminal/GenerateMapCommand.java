package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;

public class GenerateMapCommand implements Command {
    private final TerminalEngine engine;
    private final IOInterface io;

    public GenerateMapCommand(TerminalEngine engine, IOInterface io) {
        this.engine = engine;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Generate random map";
    }

    @Override
    public void execute() {
        MapManager manager = new MapManager();

        Map map = manager.generate();
        io.showMessage("Map generated successfully!");

        engine.setMap(map);
    }
}
