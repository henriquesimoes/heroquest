package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;

//** Talvez dê pra herdar um "MapListItem" para separar o comando de setar mapa desta classe genérica*/
public class BoxFreeText implements Clickable {
    private final Graphics2D graphics;
    private final String text;
    private final Font boxFont;
    private final Settings SETTINGS;
    private ScreenStateManager screenStateManager;

    private int boxBoundYCoordFix;
    private int boxBoundXCoordFix;

    public BoxFreeText(String text, Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        this.SETTINGS = settings;
        this.screenStateManager = screenStateManager;
        this.graphics = graphics;
        this.text = text;
        this.boxFont = new Font("Helvetica", Font.BOLD, 20);
    }

    //** Horizontal aligned in center */
    public void render(int y) {
        graphics.setFont(boxFont);
        graphics.setColor(Color.WHITE);
        Rectangle2D textBounds = getBounds();
        int xCoord = (GameWindow.WINDOW_WIDTH - (int) textBounds.getWidth()) / 2;

        boxBoundYCoordFix = 20 + y;
        graphics.drawString(text, xCoord, y);
    }

    @Override
    public Rectangle2D getBounds() {
        Rectangle2D textBounds = boxFont.getStringBounds(text, graphics.getFontRenderContext());
        boxBoundXCoordFix = (GameWindow.WINDOW_WIDTH - (int) textBounds.getWidth()) / 2;
        return new Rectangle2D.Double(boxBoundXCoordFix, boxBoundYCoordFix, textBounds.getWidth(), textBounds.getHeight());
    }

    @Override
    public void executeAction() {
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        try {
            SETTINGS.setMap(new MapManager().load(text));
            SETTINGS.setNickname(nickName);
            screenStateManager.setState(ScreenStates.CHOOSE_CHARACTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
