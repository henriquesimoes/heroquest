package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons;

import java.awt.*;

public class NewGameButton extends MenuButton {
    private static final String BUTTON_TEXT = "Play";

    public NewGameButton(Graphics2D graphics, int yCoord) {
        super(BUTTON_TEXT, graphics);
        setPosition(yCoord);
    }
}
