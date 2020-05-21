package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;

public class Main {

    public static void main(String[] args) {
        MapManager manager = new MapManager();
        TerminalViewer viewer = new TerminalViewer();

        Map map = manager.load("default.map");

        viewer.display(map, null);
    }
}
