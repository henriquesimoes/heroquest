package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

import java.io.PrintStream;
import java.util.Scanner;

public class TerminalIO implements IOInterface {
    private PrintStream writer;
    private Scanner reader;

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
            answer = reader.nextInt();

            if ((allowBack ? 0 : 1) <= answer && answer <= options.length)
                invalidAnswer = false;

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

    public void showSpan() {
        writer.println();
    }
}
