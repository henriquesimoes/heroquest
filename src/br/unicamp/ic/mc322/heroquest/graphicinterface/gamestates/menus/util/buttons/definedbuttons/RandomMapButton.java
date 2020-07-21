package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";;

    public RandomMapButton(Graphics2D graphics) {
        super(BUTTON_TEXT, graphics);
    }
}
