package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.GameMenu;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;

public class ScreenStateManager {
    private final MouseInput mouseInput;
    private Coordinate mouseClickCoords;

    private final GameMenu menu;

    public ScreenStateManager(Graphics2D graphics, MouseInput mouseInput) {
        this.mouseInput = mouseInput;
        menu = new GameMenu(graphics);
    }

    public void render() {
        ScreenState currentState = GameWindow.getScreenState();
        mouseClickCoords = mouseInput.getMouseCoordsOnClick();

        switch (currentState) {
            case START_MENU:
                menu.render();
        }
    }

}
