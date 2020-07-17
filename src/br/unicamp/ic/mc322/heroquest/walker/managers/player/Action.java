package br.unicamp.ic.mc322.heroquest.walker.managers.player;

public interface Action {
    String getDescription();

    boolean execute();
}
