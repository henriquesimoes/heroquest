package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;
import br.unicamp.ic.mc322.heroquest.map.view.Viewer;

public class MapManager {
    private Map map;

    public void setMap(String name) {
        MapLoader loader = new MapLoader();

        this.map = loader.load(name);
    }

    public void displayMap() {
        Viewer view = new Viewer(map);

        view.display();
    }
}
