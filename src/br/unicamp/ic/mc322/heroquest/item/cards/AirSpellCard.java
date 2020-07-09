package br.unicamp.ic.mc322.heroquest.item.cards;

import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class AirSpellCard extends SpellCard {
    public AirSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "It can be used only if character is able to learn air spells.", goldCoinsValue);
    }

    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearnAirSpell()) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        }
    }
}
