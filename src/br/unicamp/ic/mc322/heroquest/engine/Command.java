package br.unicamp.ic.mc322.heroquest.engine;

public interface Command {
    String getDescription();

    void execute();
}
