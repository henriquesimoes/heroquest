package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.character.CharacterSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning.GameRunning;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu.MapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu.StandardMapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.StartMenu;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

public class StateManager {
    private final HashMap<State, StateViewer> states;
    private Stack<StateViewer> stack;

    public StateManager(Graphics2D graphics, GamePanel gamePanel) {
        stack = new Stack<>();
        states = new HashMap<>();

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
