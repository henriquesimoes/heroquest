package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.engine.GameLoop;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.KeyboardInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu.MapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu.StandardMapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.startmenu.StartMenu;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private final BufferedImage image;
    private final Graphics2D graphics;
    private final Settings SETTINGS;
    private final ScreenStateManager screenStateManager;
    private final MouseInput mouseInput;
    private final KeyboardInput keyboardInput;
    private GraphicIO graphicIO;
    private Map map;
    private boolean running;
    private Thread gameThread;

    public GamePanel() {
        Dimension preferredSize = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);

        setPreferredSize(preferredSize);
        setFocusable(true);
        requestFocus();

        this.mouseInput = new MouseInput();
        this.keyboardInput = new KeyboardInput();

        addMouseListener(mouseInput);
        addKeyListener(keyboardInput);

        image = new BufferedImage(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.SETTINGS = new Settings();
        screenStateManager = new ScreenStateManager(mouseInput);
        screenStateManager.addState(new StartMenu(graphics, screenStateManager), ScreenStates.START_MENU);
        screenStateManager.addState(new MapSelection(graphics, SETTINGS, screenStateManager), ScreenStates.MAP_SELECTION);
        screenStateManager.addState(new StandardMapSelection(graphics, SETTINGS, screenStateManager), ScreenStates.LIST_OF_MAPS);

        MapManager mapManager = new MapManager();

        try {
            map = mapManager.load("small_02.map");
        } catch (Exception e) {
        }

        GraphicMapViewer graphicMapViewer = new GraphicMapViewer(graphics, SETTINGS, screenStateManager, map);
        this.graphicIO = new GraphicIO(mouseInput, keyboardInput, graphicMapViewer);

        Walker walker = new Wizard("G", graphicIO);
        map.add(walker);
        Thread loop = new Thread(new GameLoop(map));
        loop.start();


        screenStateManager.addState(graphicMapViewer, ScreenStates.GAME_RUNNING);

        screenStateManager.setState(ScreenStates.GAME_RUNNING);
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
                tick();
            }
            renderGraphics();
        }
    }

    private void tick() {
        screenStateManager.update(screenStateManager.getCurrentState());
    }

    private void renderGraphics() {
        renderBackGround();
        screenStateManager.render();
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
}

