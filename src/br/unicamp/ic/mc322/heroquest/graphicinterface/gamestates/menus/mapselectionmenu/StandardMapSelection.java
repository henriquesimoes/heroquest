package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxedList;

import javax.swing.*;
import java.awt.*;

public class StandardMapSelection extends JPanel {
    private final GameTitle GAME_TITLE;
    private final BoxedList LIST_OF_MAPS;

    public StandardMapSelection(Graphics2D graphics) {
        this.LIST_OF_MAPS = new BoxedList(GamePanel.manager.getExistingMapNames(), graphics);
        this.GAME_TITLE = new GameTitle(graphics, 200);
    }

    public void render() {
        GAME_TITLE.render();
        LIST_OF_MAPS.render(350);
    }
}
