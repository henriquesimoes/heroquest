package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.ChooseMapFromListOfMapsCommand;

import java.awt.*;

public class MapListItem extends BoxFreeText implements Clickable {
    private Command command;

    public MapListItem(String text, Graphics2D graphics, GraphicEngine graphicEngine) {
        super(text, graphics, graphicEngine);
        setCommand(new ChooseMapFromListOfMapsCommand(text, graphicEngine));
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    @Override
    public void executeAction() {
        executeCommand();
    }
}
