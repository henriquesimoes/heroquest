package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.map.MapManager;

public class Main {

    public static void main(String[] args) {
        MapManager manager = new MapManager();

        manager.setMap("default.map");

        manager.displayMap();
    }
}
