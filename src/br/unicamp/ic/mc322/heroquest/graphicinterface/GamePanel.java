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

    private void initGameInterface() {
        running = true;
        image = new BufferedImage(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
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

    private void renderGraphics() {
        if (graphics != null) {
            graphics.setColor(Color.RED);
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        }
    }

    private void draw() {
        Graphics showScreen = (Graphics) this.getGraphics();
        showScreen.drawImage(image, 0, 0, null);
        showScreen.dispose();
    }

}

