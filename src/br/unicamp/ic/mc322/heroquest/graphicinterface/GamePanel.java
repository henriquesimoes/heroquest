package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu.MapSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private boolean running;
    private Thread gameThread;

    private final BufferedImage image;
    private final Graphics2D graphics;

    public GamePanel() {
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

        final double ONE_SECOND_IN_NANOSECONDS = 1000000000;
        final double FRAMES_PER_SECOND = 61.0;
        final double TIME_PER_FRAME = ONE_SECOND_IN_NANOSECONDS / FRAMES_PER_SECOND;

        double lastUpdateTime = System.nanoTime();
        int framesCnt = 0;

        double firstUpdate = lastUpdateTime;
        while (running) {
            double now = System.nanoTime();
            if (now - lastUpdateTime >= TIME_PER_FRAME) {
                lastUpdateTime = now;
                tick();
                framesCnt++;
            }

            if (lastUpdateTime - firstUpdate >= ONE_SECOND_IN_NANOSECONDS) {
                firstUpdate = lastUpdateTime;
                System.out.println(framesCnt);
                framesCnt = 0;
            }
            renderGraphics();
        }
    }

    private void tick() {
    }

    private void renderGraphics() {
        if (graphics != null) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        }
        // Todo: como tratar melhor essa exceção?
        else {
            throw new NullPointerException();
        }

//        new StartMenu(graphics).render();
        new MapSelection(graphics).render();

        render();
    }

    private void render() {
        Graphics showInTheScreen = this.getGraphics();
        showInTheScreen.drawImage(image, 0, 0, null);
        showInTheScreen.dispose();
    }

}

