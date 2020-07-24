package br.unicamp.ic.mc322.heroquest.view;

public interface Command {
    /**
     * Defines the message that is to be shown to the user
     * @return description message
     */
    String getDescription();

    /**
     * Executes the defined command
     */
    void execute();
}
