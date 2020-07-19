package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.util.ArrayList;

//Todo: melhor implementar classe abstrata para guardar as váriaveis em comum
public class MapSelection implements Renderable {
    private final Graphics2D graphics;

    public MapSelection(Graphics2D graphics) {
        this.graphics = graphics;
    }


    public void render() {
        graphics.fillRect(200, 200, 200, 200);
    }

    @Override
    public ArrayList<Coordinate> getClickableZones() {
        return null;
    }
}
