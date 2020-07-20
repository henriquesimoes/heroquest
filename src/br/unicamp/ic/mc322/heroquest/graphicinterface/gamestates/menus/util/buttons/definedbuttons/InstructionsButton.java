package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;

public class InstructionsButton extends MenuButton {
    private static final String BUTTON_TEXT = "Instructions";

    public InstructionsButton(Graphics2D graphics) {
        super(BUTTON_TEXT, graphics);
    }
}
