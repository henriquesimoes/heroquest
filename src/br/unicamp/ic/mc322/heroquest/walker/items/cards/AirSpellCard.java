package br.unicamp.ic.mc322.heroquest.walker.items.cards;

import br.unicamp.ic.mc322.heroquest.walker.items.SpellCard;

public abstract class AirSpellCard extends SpellCard {
    protected AirSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "\nIt can be used only if character is able to learn air spells.", goldCoinsValue, SpellElement.AIR);
    }
}
