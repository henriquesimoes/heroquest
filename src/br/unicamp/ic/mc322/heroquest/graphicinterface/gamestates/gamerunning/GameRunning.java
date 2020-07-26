package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.engine.GameLoop;
import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.map.MapPopulator;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

import java.awt.*;
import java.util.ArrayList;

public class GameRunning implements Renderable {
    private Graphics2D graphics;
    private GraphicEngine graphicEngine;
    private GraphicGameViewer graphicGameViewer;
    private GameLoop gameLoop;
    private StateManager stateManager;
    private ArrayList<Clickable> clickableZones;
    private Map map;
    private boolean isRunning;
    private Walker player;

    public GameRunning(Graphics2D graphics, GraphicEngine graphicEngine, StateManager stateManager) {
        this.clickableZones = new ArrayList<>();
        this.graphics = graphics;
        this.graphicEngine = graphicEngine;
        this.stateManager = stateManager;
        this.isRunning = false;
    }

    void create() {
        this.map = graphicEngine.getMap();
        HeroKind heroKind = graphicEngine.getHeroKid();
        String name = graphicEngine.getHeroName();

        this.graphicGameViewer = new GraphicGameViewer(graphics, graphicEngine, map);
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
                stateManager.changeState(States.GO_TO_FIRST);
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
