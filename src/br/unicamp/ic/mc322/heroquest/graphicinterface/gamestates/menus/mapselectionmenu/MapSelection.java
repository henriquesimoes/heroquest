package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxFreeText;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.util.ArrayList;

//Todo: melhor implementar classe abstrata para guardar as váriaveis em comum
public class MapSelection implements Renderable {
    private final String RANDOM_MAP_OPTION_TEXT = "Random map";
    private final String STANDARD_MAPS_OPTION_TEXT = "Select a map";
    private final String BACK_BUTTON_TEXT = "Back";
    private final GameTitle GAME_TITLE;
    private final BoxFreeText RANDOM_MAP_BUTTON;
    private final BoxFreeText STANDARD_MAP_BUTTON;
    private final BoxFreeText BACK_BUTTON;

    public MapSelection(Graphics2D graphics) {
        this.RANDOM_MAP_BUTTON = new BoxFreeText(RANDOM_MAP_OPTION_TEXT, graphics);
        this.STANDARD_MAP_BUTTON = new BoxFreeText(STANDARD_MAPS_OPTION_TEXT, graphics);
        this.BACK_BUTTON = new BoxFreeText(BACK_BUTTON_TEXT, graphics);
        this.GAME_TITLE = new GameTitle(graphics, 200);
    }

    public void render() {
        GAME_TITLE.render();
        RANDOM_MAP_BUTTON.render(450);
        STANDARD_MAP_BUTTON.render(550);
        BACK_BUTTON.render(650);
    }

    @Override
    public ArrayList<Coordinate> getClickableZones() {
        return null;
    }
}
