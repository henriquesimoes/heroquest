package br.unicamp.ic.mc322.heroquest.engine.gui.states;

import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.character.CharacterSelection;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.gamerunning.GameRunning;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.mapselectionmenu.MapSelection;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.mapselectionmenu.StandardMapSelection;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.startmenu.StartMenu;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

public class StateManager {
    private final HashMap<State, StateViewer> states;
    private Stack<StateViewer> stack;
    private Graphics2D graphics;
    private GamePanel gamePanel;

    public StateManager(Graphics2D graphics, GamePanel gamePanel) {
        stack = new Stack<>();
        states = new HashMap<>();
        this.graphics = graphics;
        this.gamePanel = gamePanel;
    }

    public void createStates() {
        states.put(State.START_MENU, new StartMenu(graphics, gamePanel));
        states.put(State.MAP_SELECTION, new MapSelection(graphics, gamePanel));
        states.put(State.LIST_OF_MAPS, new StandardMapSelection(graphics, gamePanel));
        states.put(State.CHOOSE_CHARACTER, new CharacterSelection(graphics, gamePanel));
        states.put(State.GAME_RUNNING, new GameRunning(graphics, gamePanel, this));

        stack.push(states.get(State.START_MENU));
    }

    public StateViewer getCurrentState() {
        return stack.lastElement();
    }

    private void goTo(State state) {
        stack.add(this.states.get(state));
    }

    private void goToPrevState() {
        stack.pop();
    }

    public void goToFirst() {
        while (stack.size() > 1)
            stack.pop();
    }

    public void changeState(State state) {
        switch (state) {
            case STABLE:
                break;
            case GO_TO_PREV:
                goToPrevState();
                break;
            case GO_TO_FIRST:
                goToFirst();
                break;
            case QUIT:
                System.exit(0);
            default:
                goTo(state);
        }
    }
}
