package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

public class GenerateMapCommand implements Command {
    private TerminalEngine engine;
    private IOInterface io;

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
