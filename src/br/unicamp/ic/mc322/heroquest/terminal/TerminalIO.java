package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.view.MapViewer;

import java.io.PrintStream;
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
        boolean invalidAnswer = true;
        int answer = 0;

        while (invalidAnswer) {
            for (int i = 0; i < options.length; i++)
                writer.printf("%2d - %s\n", i + 1, options[i]);

            if (allowBack)
                writer.printf("%2d - Return\n", 0);

            writer.print("Selected option: ");

            try {
                answer = reader.nextInt();
                if ((allowBack ? 0 : 1) <= answer && answer <= options.length)
                    invalidAnswer = false;
            } catch (InputMismatchException e) {
                reader.nextLine();
                writer.printf("Invalid option");
            }

            writer.println();
        }

        return answer;
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

    @Override
    public String getStringAnswer(String question) {
        writer.print(question);
        String answer;

        do {
            answer = reader.nextLine();
        } while (answer.isBlank());

        return answer;
    }

    @Override
    public void showMap(Coordinate position) {
        viewer.display(position);
    }

    @Override
    public void setMap(Map map) {
        viewer = new TerminalMapViewer(map);
    }

    @Override
    public Direction getMoveDirection() {
        System.out.println("Type the direction of movement or Q to quit: ");
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
                    System.out.println("Invalid direction.\nType the direction of movement or Q to quit: ");
            }
        } while (!validAnswer);

        return direction;
    }

    public void showSpan() {
        writer.println();
    }
}
