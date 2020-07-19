package br.unicamp.ic.mc322.heroquest.item.cards;

import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class FireSpellCard extends SpellCard {
    protected FireSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "It can be used only if character is able to learn fire spells.", goldCoinsValue);
    }

    @Override
    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearnFireSpell()) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        } else
            proprietary.getManager().showMessage("You are not able to learn this spell");
    }
}
