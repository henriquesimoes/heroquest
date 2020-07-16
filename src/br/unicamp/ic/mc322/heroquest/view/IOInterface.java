package br.unicamp.ic.mc322.heroquest.view;

public interface IOInterface {
    int showOptionsAndGetAnswer(String[] options, boolean allowBack);

    void selectAndExecute(Command[] commands, boolean allowBack);

    void showMessage(String message);

    void showError(String message);

    String getStringAnswer(String question);
}
