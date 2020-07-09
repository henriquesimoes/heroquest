package br.unicamp.ic.mc322.heroquest.item.cards;

import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class EarthSpellCard extends SpellCard {

    public EarthSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "It can be used only if character is able to learn earth spells.", goldCoinsValue);
    }

    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearnEarthSpell()) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        }
    }
}
