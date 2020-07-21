package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;

public class ListOfExistentMapsButton extends MenuButton {
    private static final String BUTTON_TEXT = "Select map";

    public ListOfExistentMapsButton(Graphics2D graphics) {
        super(BUTTON_TEXT, graphics);
    }
}
