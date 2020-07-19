package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons;

import java.awt.*;

public class InstructionsButton extends MenuButton {
    private static final String BUTTON_TEXT = "Instructions";

    public InstructionsButton(Graphics2D graphics, int yCoord) {
        super(BUTTON_TEXT, graphics);
        setPosition(yCoord);
    }
}
