package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons;

import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;

public abstract class MenuButton extends GenericButton{
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;

    public MenuButton(String innerText, Graphics2D graphics) {
        super(innerText, graphics);
        setFontSize(20);
        setBoxColor(new Color(61, 145, 166));
        setBoxDimensions(new Dimension(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT));
    }

    public void render(int y) {
        setPosition(y);
        super.render();
    }


}
