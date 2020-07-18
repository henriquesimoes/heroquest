package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import java.awt.*;

//Todo: melhor implementar classe abstrata para guardar as váriaveis em comum
public class MapSelection implements Renderable {
    private Graphics2D graphics;

    public MapSelection(Graphics2D graphics) {
        this.graphics = graphics;
    }


    public void render() {
        graphics.fillRect(200, 200, 200, 200);
    }
}
