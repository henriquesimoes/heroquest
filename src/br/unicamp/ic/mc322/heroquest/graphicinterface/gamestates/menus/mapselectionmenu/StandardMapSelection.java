package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxedList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StandardMapSelection {
    private BoxedList mapList;

    public StandardMapSelection(Graphics2D graphics) {
        this.mapList = new BoxedList(GamePanel.manager.getExistingMapNames(), graphics);

        mapList.render(100);
    }
}
