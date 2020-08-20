package br.unicamp.ic.mc322.heroquest.walker.items.cards;

import br.unicamp.ic.mc322.heroquest.walker.items.SpellCard;

public abstract class FireSpellCard extends SpellCard {
    protected FireSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "\nIt can be used only if character is able to learn fire spells.", goldCoinsValue, SpellElement.FIRE);
    }
}
