package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.character;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.Card;

import java.awt.*;
import java.util.ArrayList;

public class CharacterSelection implements Renderable {
    Graphics2D graphics;

    public CharacterSelection(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void render() {
        new Card("teste", "teste", graphics).render(20, 20);
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return new ArrayList<Clickable>();
    }
}
