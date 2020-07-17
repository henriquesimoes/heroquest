package br.unicamp.ic.mc322.heroquest.walker.managers;

public interface Action {
    String getDescription();

    boolean execute();
}
