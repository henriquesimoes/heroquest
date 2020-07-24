package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class CharacterCard extends Card {
    private HeroesKind heroKind;
    private final Settings SETTINGS;

    public CharacterCard(HeroesKind heroKind, String name, String description, Settings settings, Graphics2D graphics) {
        super(name, description, graphics);

        this.SETTINGS = settings;
        this.heroKind = heroKind;
    }

    @Override
    public void executeAction() {
        SETTINGS.setWalker(heroKind);
    }
}
