package br.unicamp.ic.mc322.heroquest.view;

public interface Command {
    String getDescription();

    void execute();
}
