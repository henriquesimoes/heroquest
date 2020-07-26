package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.States;
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
    private GraphicEngine graphicEngine;

    private int boxBoundCoordFixY;
    private int boxBoundCoordFixX;

    public BoxFreeText(String text, Graphics2D graphics, GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
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

        boxBoundCoordFixY = y - (int) textBounds.getHeight();
        graphics.drawString(text, xCoord, y);
    }

    @Override
    public Rectangle2D getBounds() {
        Rectangle2D textBounds = boxFont.getStringBounds(text, graphics.getFontRenderContext());
        boxBoundCoordFixX = (GameWindow.WINDOW_WIDTH - (int) textBounds.getWidth()) / 2;
        return new Rectangle2D.Double(boxBoundCoordFixX, boxBoundCoordFixY, textBounds.getWidth(), textBounds.getHeight());
    }

    @Override
    public States executeAction() {
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        try {
            graphicEngine.setMap(new MapManager().load(text));
            graphicEngine.setName(nickName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return States.CHOOSE_CHARACTER;
    }
}
