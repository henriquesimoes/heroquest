package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;

import java.io.FileNotFoundException;

public class ChooseMapCommand implements Command {
    private final IOInterface io;
    private final TerminalEngine engine;

    public ChooseMapCommand(TerminalEngine engine, TerminalIO io) {
        this.engine = engine;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Choose existing map";
    }

    @Override
    public void execute() {
        MapManager manager = new MapManager();

        String[] mapOptions = manager.getExistingMapNames();

        io.showMessage("Choose a predefined map: ");
        int choice = io.showOptionsAndGetAnswer(mapOptions, true) - 1;

        if (choice == -1)
            return;

        try {
            Map map = manager.load(mapOptions[choice]);

            engine.setMap(map);
        } catch (FileNotFoundException ex) {
            io.showError("The " + mapOptions[choice] + " map is missing... " +
                    "An error might have occurred during installation.");
        } catch (CorruptedConfigurationFileException ex) {
            io.showError("The " + mapOptions[choice] + " is corrupted...");
            io.showError(ex.getMessage());
        }
    }
}
