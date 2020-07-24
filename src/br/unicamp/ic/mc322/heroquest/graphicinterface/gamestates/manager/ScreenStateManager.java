package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenStateManager {
    private final Map<ScreenStates, Renderable> states;
    private final MouseInput mouseInput;
    private Renderable prevState;
    private Renderable currentState;

    public ScreenStateManager(MouseInput mouseInput) {
        states = new HashMap<>();
        this.mouseInput = mouseInput;
    }

    public void addState(Renderable state, ScreenStates stateName) {
        states.put(stateName, state);
    }

    public void render() {
        currentState.render();
    }

    public void update(Renderable state) {
        ArrayList<Clickable> clickableZones = state.getClickableZones();

        for (Clickable clickable : clickableZones) {
            Rectangle2D bounds = clickable.getBounds();
            Coordinate mouseCoords = mouseInput.getMouseCoordsOnClick();

            if (bounds.contains(mouseCoords.getX(), mouseCoords.getY())) {
                mouseInput.clear();
                clickable.executeAction();
            }
        }
    }

    public void setState(ScreenStates state) {
        prevState = getCurrentState();
        currentState = states.get(state);
    }

    public Renderable getCurrentState() {
        return currentState;
    }

    public Renderable getPrevState() {
        return prevState;
    }
}
