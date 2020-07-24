package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.KeyboardInput;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gameevents.MouseInput;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

import java.io.PrintStream;

public class GraphicIO implements IOInterface {
    private final PrintStream writer;
    private final MouseInput mouseInput;
    private final KeyboardInput keyboardInput;
    private GraphicMapViewer graphicMapViewer;

    public GraphicIO(MouseInput mouseInput, KeyboardInput keyboardInput, GraphicMapViewer graphicMapViewer) {
        writer = System.out;
        this.mouseInput = mouseInput;
        this.keyboardInput = keyboardInput;
        this.graphicMapViewer = graphicMapViewer;
    }

    @Override
    public int showOptionsAndGetAnswer(String[] options, boolean allowBack) {
        boolean invalidAnswer = true;
        int answer = 0;

        while (invalidAnswer) {
            for (int i = 0; i < options.length; i++)
                writer.printf("%2d - %s\n", i + 1, options[i]);

            if (allowBack)
                writer.printf("%2d - Return\n", 0);

            writer.print("Selected option: ");

            try {
                answer = Integer.parseInt("" + keyboardInput.getKey());
                if ((allowBack ? 0 : 1) <= answer && answer <= options.length)
                    invalidAnswer = false;
            } catch (Exception e) {
                writer.print("Invalid option");
            }

            writer.println();
        }

        return answer;
    }

    @Override
    public void selectAndExecute(Command[] commands, boolean allowBack) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public String getStringAnswer(String question) {
        return null;
    }

    @Override
    public void showMap(Coordinate position) {
        graphicMapViewer.setNeedUpdateMap();
    }

    @Override
    public void setMap(Map map) {

    }

    @Override
    public Direction getMoveDirection() {
        writer.println("Type the direction of movement or Q to quit: ");
        Direction direction = null;
        boolean validAnswer;

        do {

            String answer = ("" + keyboardInput.getKey()).toUpperCase();
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
                    writer.println("Invalid direction.\nType the direction of movement or Q to quit: ");
            }
        } while (!validAnswer);

        return direction;
    }

    @Override
    public Coordinate getCoordinate(Coordinate[] coordinates) {
        return null;
    }
}
