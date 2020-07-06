package br.unicamp.ic.mc322.heroquest.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private BufferedImage image;
    private boolean running;
    private Graphics2D graphics;
    private Thread gameThread;

    public GamePanel() {
        Dimension preferredSize = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);

        setPreferredSize(preferredSize);
        setFocusable(true);
        requestFocus();
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
        initGameInterface();

        while (running) {
            renderGraphics();
            draw();
        }
    }

    private void initGameInterface() {
        running = true;
        image = new BufferedImage(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D) image.createGraphics();
    }

    private void renderGraphics() {
        if (graphics != null) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        }
    }

    private void draw() {
        Graphics showInTheScreen = (Graphics) this.getGraphics();
        showInTheScreen.drawImage(image, 0, 0, null);
        showInTheScreen.dispose();
    }

}

