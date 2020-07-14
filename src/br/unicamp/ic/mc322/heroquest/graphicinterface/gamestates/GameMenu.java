package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;

public class GameMenu {
    private final String GAME_TITLE = "Heroes&Dungeons";
    private final int SELECTION_BOX_WIDTH = 200;
    private final int SELECTION_BOX_HEIGHT = 50;

    private Graphics2D graphics2D;
    Font titleFont;
    private Font menuFont;

    public GameMenu(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        titleFont = new Font("Helvetica", Font.BOLD, 50);
        menuFont = new Font("Helvetica", Font.BOLD, 20);
    }

    public void render() {
        FontMetrics menuFontMetrics = graphics2D.getFontMetrics(menuFont);
        FontMetrics titleMetrics = graphics2D.getFontMetrics(titleFont);

        renderGameTitle(titleMetrics);

        renderPlayButton(menuFontMetrics);

        renderInstructionsButton(menuFontMetrics);

        renderQuitButton(menuFontMetrics);
    }


    private void renderGameTitle(FontMetrics fontMetrics) {

        graphics2D.setFont(titleFont);
        graphics2D.setColor(Color.WHITE);

        graphics2D.drawString(GAME_TITLE, (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(GAME_TITLE)) / 2, 100);
    }

    private void renderPlayButton(FontMetrics fontMetrics) {
        String playButton = "New Game";

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(playButton, (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(playButton)) / 2, 230);
        renderSelectionBox(200);
    }

    private void renderInstructionsButton(FontMetrics fontMetrics) {
        String instructionsButton = "Instructions";

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(instructionsButton, (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(instructionsButton)) / 2, 310);
        renderSelectionBox(280);
    }

    private void renderQuitButton(FontMetrics fontMetrics) {
        String quitButton = "Quit";

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(quitButton, (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(quitButton)) / 2, 390);
        renderSelectionBox(360);
    }



    private void renderSelectionBox(int yCoordinate) {
        int centralizedXCoordinate = (GameWindow.WINDOW_WIDTH - SELECTION_BOX_WIDTH) / 2;

        graphics2D.setColor(new Color(61, 145, 166));
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRect(centralizedXCoordinate, yCoordinate, SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
    }
}
