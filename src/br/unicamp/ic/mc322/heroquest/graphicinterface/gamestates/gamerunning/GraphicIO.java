package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.KeyboardInput;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;

public class GraphicIO implements IOInterface {
    private final KeyboardInput keyboardInput;
    private GraphicGameViewer graphicGameViewer;
    private volatile boolean waitingCoordinate;
    private volatile Coordinate clickedCoordinate;

    public GraphicIO(KeyboardInput keyboardInput, GraphicGameViewer graphicGameViewer) {
        this.keyboardInput = keyboardInput;
        this.graphicGameViewer = graphicGameViewer;
    }

    void clear() {
        graphicGameViewer.clear();
    }

    void appendMessage(String message) {
        graphicGameViewer.appendMessage(message);
    }

    @Override
    public int showOptionsAndGetAnswer(String[] options, boolean allowBack) {
        boolean invalidAnswer = true;
        int answer = 0;
        while (invalidAnswer) {
            for (int i = 0; i < options.length; i++)
                appendMessage(String.format("%2d - %s\n", i + 1, options[i]));

            if (allowBack)
                appendMessage(String.format("%2d - Return\n", 0));

            try {
                answer = Integer.parseInt(keyboardInput.getKey());
                clear();
                if ((allowBack ? 0 : 1) <= answer && answer <= options.length)
                    invalidAnswer = false;
            } catch (Exception e) {
                clear();
                appendMessage(String.format("Invalid option\n"));
            }
        }

        return answer;
    }

    @Override
    public void selectAndExecute(Command[] commands, boolean allowBack) {

    }


    @Override
    public void showMessage(String message) {
        appendMessage(message + "\n\n");
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public boolean getBooleanAnswer(String question) {
        appendMessage(question + " (N / y)\n");
        String answer = keyboardInput.getKey();
        return answer.equals("Y");
    }

    @Override
    public void showMap(Coordinate position) {
        graphicGameViewer.setNeedUpdateMap(position);
    }

    @Override
    public void showStatus(String message) {
        graphicGameViewer.setStatus(message);
    }

    @Override
    public void setMap(Map map) {

    }

    @Override
    public Direction getMoveDirection() {
        appendMessage("Type the direction of movement or Q to quit:\n");
        Direction direction = null;
        boolean validAnswer;

        do {
            String answer = keyboardInput.getKey();
            clear();
            validAnswer = true;
            switch (answer) {
                case "W":
                    direction = Direction.NORTH;
                    break;
                case "S":
                    direction = Direction.SOUTH;
                    break;
                case "A":
                    direction = Direction.WEST;
                    break;
                case "D":
                    direction = Direction.EAST;
                    break;
                case "Q":
                    direction = null;
                    break;
                default:
                    validAnswer = false;
                    appendMessage("Invalid direction.\nType the direction of movement or Q to quit\n");
            }
        } while (!validAnswer);
        return direction;
    }

    @Override
    public Coordinate getCoordinate(Coordinate[] coordinates) {
        Coordinate coordinate;
        while (true) {
            appendMessage("Click on a coordinate\n");
            coordinate = getClickedCoordinate();
            clear();
            for (int i = 0; i < coordinates.length; i++) {
                if (coordinates[i].equals(coordinate))
                    return coordinate;
            }
            appendMessage("Invalid Coordinate\n");
        }
    }


    public void changeState(int x, int y) {
        if (waitingCoordinate) {
            waitingCoordinate = false;
            clickedCoordinate = new Coordinate(x, y);
        }
    }

    public Coordinate getClickedCoordinate() {
        waitingCoordinate = true;
        while (waitingCoordinate) Thread.onSpinWait();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return graphicGameViewer.shiftClickedCoordinate(clickedCoordinate);
    }
}
