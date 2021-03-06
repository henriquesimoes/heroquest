package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.gametitle;

import br.unicamp.ic.mc322.heroquest.engine.gui.GameWindow;
import br.unicamp.ic.mc322.heroquest.engine.gui.tools.ImageEditor;
import br.unicamp.ic.mc322.heroquest.engine.gui.tools.ImageLoader;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GameTitle {
    private static final String TITLE = "HeroQuest";
    private final Graphics2D graphics;
    private final Font titleFont = new Font("Helvetica", Font.BOLD, 60);
    private BufferedImage title;
    private int yCoord;
    private int xCoord;

    //** Horizontal aligned in center*/
    public GameTitle(Graphics2D graphics, int yCoord) {
        this.graphics = graphics;
        this.yCoord = yCoord;
        this.title = ImageEditor.scaleImage(ImageLoader.readImage("title.png"), 3, 3);
        alignCenter();
    }

    private void alignCenter() {
        Rectangle2D titleBounds = titleFont.getStringBounds(TITLE, graphics.getFontRenderContext());
        int stringWidth = (int) titleBounds.getWidth();
        xCoord = (GameWindow.WINDOW_WIDTH - stringWidth) / 2 - 120;
    }

    public void render() {
        graphics.setFont(titleFont);
        graphics.setColor(Color.WHITE);
//        graphics.drawString(TITLE, xCoord, yCoord);
        graphics.drawImage(title, xCoord, yCoord, null);
    }
}
