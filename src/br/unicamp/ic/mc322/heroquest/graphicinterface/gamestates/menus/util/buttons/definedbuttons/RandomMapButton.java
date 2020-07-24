package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";
    private final Settings SETTINGS;

    public RandomMapButton(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        super(BUTTON_TEXT, graphics, screenStateManager);
        this.SETTINGS = settings;
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public void executeAction() {
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        SETTINGS.setMap(new MapManager().generate());
        SETTINGS.setNickname(nickName);
        getScreenStateManager().setState(ScreenStates.CHOOSE_CHARACTER);
    }
}
