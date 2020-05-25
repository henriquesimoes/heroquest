package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        MapManager manager = new MapManager();
        TerminalViewer viewer = new TerminalViewer();

        try {
            Map map = manager.load("default.map");

            viewer.display(map, null);
        } catch (FileNotFoundException ex) {
            System.err.println("Configuration file not found...");
        } catch (CorruptedConfigurationFileException ex) {
            System.err.println("Configuration file is corrupted...");
            System.err.println(ex.getMessage());
        }
    }
}
