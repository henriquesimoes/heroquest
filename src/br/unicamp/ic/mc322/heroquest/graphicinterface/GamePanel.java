package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.KeyboardInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.GameFPSManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private final BufferedImage image;
    private final Graphics2D graphics;
    private final MouseInput mouseInput;
    private final KeyboardInput keyboardInput;
    private StateManager stateManager;
    private boolean running;
    private Thread gameThread;
    private Map map;
    private String heroName;
    private HeroKind heroKind;

    public GamePanel() {
        this.mouseInput = new MouseInput();
        this.keyboardInput = new KeyboardInput();

        Dimension preferredSize = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);

        setPreferredSize(preferredSize);
        setFocusable(true);
        requestFocus();

        addMouseListener(mouseInput);
        addKeyListener(keyboardInput);

        image = new BufferedImage(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.stateManager = new StateManager(graphics, this);
        this.stateManager.createStates();
    }

    private StateViewer getCurrentState() {
        return stateManager.getCurrentState();
    }

    public void render() {
        getCurrentState().render();
    }

    public void update() {
        ArrayList<Clickable> clickableZones = getCurrentState().getClickableZones();
        Coordinate mouseCoords = mouseInput.getMouseCoordsOnClick();
        for (Clickable clickable : clickableZones) {
            Rectangle2D bounds = clickable.getBounds();

            if (bounds.contains(mouseCoords.getX(), mouseCoords.getY())) {
                mouseInput.clear();
                clickable.executeAction();
            }
        }
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

        GameFPSManager fpsController = new GameFPSManager();

        while (running) {
            if (fpsController.shouldUpdate()) {
                renderGraphics();
                update();
            }
        }
    }

    private void renderGraphics() {
        renderBackGround();
        getCurrentState().render();
        renderAll();
    }

    private void renderBackGround() {
        if (graphics != null) {
            graphics.setColor(new Color(41, 43, 46));
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        } else {
            throw new NullPointerException();
        }
    }

    private void renderAll() {
        Graphics showInTheScreen = this.getGraphics();
        showInTheScreen.drawImage(image, 0, 0, null);
        showInTheScreen.dispose();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public HeroKind getHeroKind() {
        return heroKind;
    }

    public void setHeroKind(HeroKind heroKind) {
        this.heroKind = heroKind;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
