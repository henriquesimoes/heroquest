package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoxFreeText {
    private Graphics2D graphics;
    private String text;
    private Font boxFont;
    private Rectangle2D textBounds;

    public BoxFreeText(String text, Graphics2D graphics) {
        this.graphics = graphics;
        this.text = text;
        this.boxFont = new Font("Helvetica", Font.BOLD, 20);
    }

    //** Horizontal aligned in center */
    public void render(int y) {
        graphics.setFont(boxFont);
        graphics.setColor(Color.WHITE);
        Rectangle2D textBounds = getTextBounds();
        int xCoord = (GameWindow.WINDOW_WIDTH - (int) textBounds.getWidth()) / 2;

        graphics.drawString(text, xCoord, y);
    }

    public Rectangle2D getTextBounds() {
        return boxFont.getStringBounds(text, graphics.getFontRenderContext());
    }
}
