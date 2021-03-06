package br.unicamp.ic.mc322.heroquest.engine.gui.states.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.engine.GameLoop;
import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.engine.gui.Clickable;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.State;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateManager;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateViewer;
import br.unicamp.ic.mc322.heroquest.map.MapPopulator;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;
import java.util.ArrayList;

public class GameRunning implements StateViewer {
    private Graphics2D graphics;
    private GamePanel gamePanel;
    private GraphicGameViewer graphicGameViewer;
    private GameLoop gameLoop;
    private StateManager stateManager;
    private ArrayList<Clickable> clickableZones;
    private Map map;
    private boolean isRunning;
    private Walker player;

    public GameRunning(Graphics2D graphics, GamePanel gamePanel, StateManager stateManager) {
        this.clickableZones = new ArrayList<>();
        this.graphics = graphics;
        this.gamePanel = gamePanel;
        this.stateManager = stateManager;
        this.isRunning = false;
    }

    void create() {
        this.map = gamePanel.getMap();
        HeroKind heroKind = gamePanel.getHeroKind();
        String name = gamePanel.getHeroName();

        this.graphicGameViewer = new GraphicGameViewer(graphics, gamePanel, map);
        GraphicIO graphicIO = graphicGameViewer.getGraphicIO();
        clickableZones = graphicGameViewer.getClickableZones();

        this.player = heroKind.getHero(name, graphicIO);
        map.add(player);

        MapPopulator populator = new MapPopulator(GameLevel.EASY);
        populator.populate(map);
        GameMonitor.getInstance().subscribe(map);

        this.gameLoop = new GameLoop(map);
        Thread loop = new Thread(gameLoop);
        loop.start();
        while (!gameLoop.isRunning()) ;
    }

    @Override
    public void render() {
        if (isRunning) {
            if (gameLoop.isRunning()) {
                graphicGameViewer.render();
            } else {
                stateManager.changeState(State.GO_TO_FIRST);
                GameMonitor.getInstance().unsubscribe(map);
                isRunning = false;
            }
        } else {
            isRunning = true;
            create();
        }
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return clickableZones;
    }
}
