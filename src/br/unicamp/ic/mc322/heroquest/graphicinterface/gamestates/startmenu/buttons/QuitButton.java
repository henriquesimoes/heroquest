package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons;

import java.awt.*;

public class QuitButton extends MenuButton {
    private static final String BUTTON_TEXT = "Quit";

    public QuitButton(Graphics2D graphics, int yCoord) {
        super(BUTTON_TEXT, graphics);
        setPosition(yCoord);
    }
}
