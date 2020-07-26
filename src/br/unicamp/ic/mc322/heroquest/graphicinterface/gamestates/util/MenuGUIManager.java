package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.*;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes.BoxedMapList;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;

public class MenuGUIManager {
    Graphics2D graphics;
    GraphicEngine graphicEngine;

    public MenuGUIManager(Graphics2D graphics, GraphicEngine graphicEngine) {
        this.graphics = graphics;
        this.graphicEngine = graphicEngine;
    }

    public GameTitle getGameTitle(int y) {
        return new GameTitle(graphics, y);
    }

    public MenuButton getRandomMapButton() {
        return new MenuButton("Random map",
                new GenerateRandomMapCommand(graphicEngine),
                graphics,
                graphicEngine);
    }

    public MenuButton getGoToMapSelectionButton() {
        return new MenuButton("Select Map",
                new ShowListOfMapsCommand(graphicEngine.getStateManager()),
                graphics,
                graphicEngine);
    }

    public MenuButton getNewGameButton() {
        return new MenuButton("Play",
                new NewGameCommand(graphicEngine.getStateManager()),
                graphics,
                graphicEngine);
    }

    public MenuButton getBackButton() {
        return new MenuButton("BACK",
                new BackToPrevStateCommand(graphicEngine.getStateManager()),
                graphics,
                graphicEngine);
    }

    public MenuButton getQuitButton() {
        return new MenuButton("Quit", new QuitCommand(), graphics, graphicEngine);
    }

    public BoxedMapList getBoxedMapList(MapManager mapManager) {
        return new BoxedMapList(mapManager.getExistingMapNames(), graphics, graphicEngine);
    }
}
