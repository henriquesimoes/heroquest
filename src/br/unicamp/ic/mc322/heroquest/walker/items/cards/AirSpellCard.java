package br.unicamp.ic.mc322.heroquest.walker.items.cards;

import br.unicamp.ic.mc322.heroquest.walker.items.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class AirSpellCard extends SpellCard {
    protected AirSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "It can be used only if character is able to learn air spells.", goldCoinsValue);
    }

    @Override
    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearnAirSpell()) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        } else
            proprietary.getManager().showMessage("You are not able to learn this spell");
    }
}
