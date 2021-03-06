package br.unicamp.ic.mc322.heroquest.engine.gui.states.util;

import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands.*;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.selectionboxes.BoxedMapList;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;

public class GUIMenuFactory {
    Graphics2D graphics;
    GamePanel gamePanel;

    public GUIMenuFactory(Graphics2D graphics, GamePanel gamePanel) {
        this.graphics = graphics;
        this.gamePanel = gamePanel;
    }

    public GameTitle getGameTitle(int y) {
        return new GameTitle(graphics, y);
    }

    public MenuButton getRandomMapButton() {
        return new MenuButton("Random map",
                new GenerateRandomMapCommand(gamePanel),
                graphics,
                gamePanel);
    }

    public MenuButton getGoToMapSelectionButton() {
        return new MenuButton("Select map",
                new ShowListOfMapsCommand(gamePanel.getStateManager()),
                graphics,
                gamePanel);
    }

    public MenuButton getNewGameButton() {
        return new MenuButton("Play",
                new NewGameCommand(gamePanel.getStateManager()),
                graphics,
                gamePanel);
    }

    public MenuButton getBackButton() {
        return new MenuButton("BACK",
                new BackToPrevStateCommand(gamePanel.getStateManager()),
                graphics,
                gamePanel);
    }

    public MenuButton getQuitButton() {
        return new MenuButton("Quit", new QuitCommand(), graphics, gamePanel);
    }

    public BoxedMapList getBoxedMapList(MapManager mapManager) {
        return new BoxedMapList(mapManager.getExistingMapNames(), graphics, gamePanel);
    }
}
