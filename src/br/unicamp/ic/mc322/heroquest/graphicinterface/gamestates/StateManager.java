package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.character.CharacterSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning.GameRunning;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu.MapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu.StandardMapSelection;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.StartMenu;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

public class StateManager {
    private final HashMap<States, Renderable> states;
    private Stack<Renderable> stack;

    public StateManager(Graphics2D graphics, GraphicEngine graphicEngine) {
        stack = new Stack<>();
        states = new HashMap<>();

        states.put(States.START_MENU, new StartMenu(graphics, graphicEngine));
        states.put(States.MAP_SELECTION, new MapSelection(graphics, graphicEngine));
        states.put(States.LIST_OF_MAPS, new StandardMapSelection(graphics, graphicEngine));
        states.put(States.CHOOSE_CHARACTER, new CharacterSelection(graphics, graphicEngine));
        states.put(States.GAME_RUNNING, new GameRunning(graphics, graphicEngine, this));

        stack.push(states.get(States.START_MENU));
    }

    public Renderable getCurrentState() {
        return stack.lastElement();
    }

    private void goTo(States states) {
        stack.add(this.states.get(states));
    }

    private void goToPrevState() {
        stack.pop();
    }

    public void goToFirst() {
        while (stack.size() > 1)
            stack.pop();
    }

    public void changeState(States states) {
        switch (states) {
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
                goTo(states);
        }
    }
}
