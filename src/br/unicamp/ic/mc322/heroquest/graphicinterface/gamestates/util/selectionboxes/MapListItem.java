package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.ChooseMapFromListOfMapsCommand;

import java.awt.*;

public class MapListItem extends BoxFreeText implements Clickable {
    private Command command;

    public MapListItem(String text, Graphics2D graphics, GamePanel gamePanel) {
        super(text, graphics, gamePanel);
        setCommand(new ChooseMapFromListOfMapsCommand(text, gamePanel));
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void executeAction() {
        command.execute();
    }
}
