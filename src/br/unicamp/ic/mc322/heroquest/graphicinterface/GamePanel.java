package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu.MapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu.StandardMapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.startmenu.StartMenu;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private boolean running;
    private Thread gameThread;

    private final BufferedImage image;
    private final Graphics2D graphics;
    //TODO: temporariamente static
    public static Map map;
    public static MapManager manager;

    public GamePanel(Map map) {
        GamePanel.map = map;
        GamePanel.manager = new MapManager();
        Dimension preferredSize = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);

        setPreferredSize(preferredSize);
        setFocusable(true);
        requestFocus();

        image = new BufferedImage(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (gameThread == null) {
            gameThread = new Thread(this, "gameThread");
            gameThread.start();
        }
    }

    @Override
    public void run() {
        running = true;

        GameFPSManager teste = new GameFPSManager();

        while (running) {
            if (teste.shouldUpdate()) {
                tick();
            }

            renderGraphics();
        }
    }

    private void tick() {
    }

    private void renderGraphics() {
        if (graphics != null) {
            graphics.setColor(new Color(41, 43, 46));
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        }
        // Todo: como tratar melhor essa exceção?
        else {
            throw new NullPointerException();
        }

        new StartMenu(graphics).render();
//        new MapSelection(graphics).render();
//        new StandardMapSelection(graphics).render();
        render();
    }

    private void render() {
        Graphics showInTheScreen = this.getGraphics();
        showInTheScreen.drawImage(image, 0, 0, null);
        showInTheScreen.dispose();
    }
}

