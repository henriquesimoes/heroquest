package br.unicamp.ic.mc322.heroquest.item.spellCard.fireSpellCard;

import br.unicamp.ic.mc322.heroquest.item.spellCard.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class FireSpellCard extends SpellCard {

    public FireSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "It can be used only if character is able to learn fire spells.", goldCoinsValue);
    }

    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearnFireSpell()) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        }
    }
}
