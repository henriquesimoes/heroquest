package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamemenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class GameMenu extends Component implements Renderable {
    private final String GAME_TITLE = "Heroes&Dungeons";

    private final Graphics2D graphics2D;
    Font titleFont;
    private final Font menuFont;

    public GameMenu(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        titleFont = new Font("Helvetica", Font.BOLD, 50);
        menuFont = new Font("Helvetica", Font.BOLD, 20);
    }

    public ArrayList<Coordinate> getClickableZones() {

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
        int alignToHorizontalCenter = (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(ButtonType.NEW_GAME.getValue())) / 2;

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(ButtonType.NEW_GAME.getValue(), alignToHorizontalCenter, 230);
        renderSelectionBox(200);
    }

    private void renderInstructionsButton(FontMetrics fontMetrics) {
        int alignToHorizontalCenter = (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth()) / 2;

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(ButtonType.INSTRUCTIONS.getValue(), alignToHorizontalCenter, 310);
        renderSelectionBox(280);
    }

    private void renderQuitButton(FontMetrics fontMetrics) {
        int alignToHorizontalCenter = (GameWindow.WINDOW_WIDTH - fontMetrics.stringWidth(ButtonType.QUIT.getValue())) / 2;

        graphics2D.setFont(menuFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(ButtonType.QUIT.getValue(), alignToHorizontalCenter, 390);
        renderSelectionBox(360);
    }


}
