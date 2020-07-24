package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

import java.awt.*;

public class BarbarianChar extends CharacterCard {


    public BarbarianChar(Image characterImage, HeroesKind heroKind, String name, String description, Settings settings, Graphics2D graphics) {
        super(characterImage, heroKind, name, description, settings, graphics);
    }
}
