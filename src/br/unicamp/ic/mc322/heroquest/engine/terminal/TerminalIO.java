package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalIO implements IOInterface {
    private final PrintStream writer;
    private final Scanner reader;
    private MapViewer viewer;

    public TerminalIO() {
        writer = System.out;
        reader = new Scanner(System.in);
    }

    @Override
    public int showOptionsAndGetAnswer(String[] options, boolean allowBack) {
        while (true) {
            for (int i = 0; i < options.length; i++)
                writer.printf("%2d - %s\n", i + 1, options[i]);

            if (allowBack)
                writer.printf("%2d - Return\n", 0);

            writer.print("Selected option: ");

            try {
                int answer = reader.nextInt();
                if ((allowBack ? 0 : 1) <= answer && answer <= options.length)
                    return answer;
            } catch (InputMismatchException e) {
                reader.nextLine();
            }

            writer.print("Invalid option");
            writer.println();
        }
    }

    @Override
    public void selectAndExecute(Command[] commands, boolean allowBack) {
        String[] options = new String[commands.length];

        for (int i = 0; i < commands.length; i++)
            options[i] = commands[i].getDescription();

        int choice = showOptionsAndGetAnswer(options, allowBack) - 1;

        if (choice == -1)
            return;

        commands[choice].execute();
    }

    @Override
    public void showMessage(String message) {
        writer.println(message);
    }

    @Override
    public void showError(String message) {
        writer.println(message);
    }

    /**
     * Gets a string answer from the user. This string may contain more than one word.
     * Moreover, an answer is always a non-blank string.
     *
     * @param question - message in which the user will answer
     * @return non-blank answer
     */
    public String getStringAnswer(String question) {
        writer.print(question);
        String answer;

        do {
            answer = reader.nextLine();
        } while (answer.isBlank());

        showSpan();

        return answer;
    }

    @Override
    public boolean getBooleanAnswer(String question) {
        String[] positiveAnswers = {"yes", "y", "yep"};

        String answer = getStringAnswer(question + " (yes / no) ");

        return Arrays.asList(positiveAnswers).contains(answer);
    }

    @Override
    public void showMap(Coordinate position) {
        viewer.display(position);
    }

    @Override
    public void showStatus(String messsage) {
        showMessage(messsage);
    }

    @Override
    public void setMap(Map map) {
        viewer = new TerminalMapViewer(map);
    }

    @Override
    public Direction getMoveDirection() {
        writer.println("Type the direction of movement or Q to quit: ");
        String answer = "";
        Direction direction = null;
        boolean validAnswer;

        do {
            while (answer.isBlank())
                answer = reader.nextLine();
            answer = answer.toUpperCase();

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
                    answer = "";
                    writer.println("Invalid direction.\nType the direction of movement or Q to quit: ");
            }
        } while (!validAnswer);

        showSpan();

        return direction;
    }

    @Override
    public Coordinate getCoordinate(Coordinate[] coordinates) {
        Coordinate chosenCoordinate = null;

        while (chosenCoordinate == null) {
            String answer = "";
            writer.print("Type the destination coordinate as two numbers separated by spaces or Q to exit: ");

            while (answer.isBlank())
                answer = reader.nextLine();
            answer = answer.toUpperCase();

            if (answer.equals("Q"))
                break;

            try {
                String[] numbers = answer.split(" ");
                int coordinateX = Integer.parseInt(numbers[0]);
                int coordinateY = Integer.parseInt(numbers[1]);
                Coordinate coordinate = new Coordinate(coordinateX, coordinateY);

                if (Arrays.asList(coordinates).contains(coordinate))
                    chosenCoordinate = coordinate;
                else {
                    writer.println("This coordinate is not a valid option.");
                }
            } catch (IndexOutOfBoundsException e) {
                writer.println("Please, type the two numbers on the same line");
            } catch (NumberFormatException e) {
                writer.println("Please, type only numbers.");
            }
        }
        showSpan();

        return chosenCoordinate;
    }

    public void showSpan() {
        writer.println();
    }
}
