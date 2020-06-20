package br.unicamp.ic.mc322.heroquest.item.spellCard.earthSpellCard;

import br.unicamp.ic.mc322.heroquest.item.spellCard.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class EarthSpellCard extends SpellCard {

    public EarthSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "Can be used only if character is able to learn earth spells.", goldCoinsValue);
    }

    public void useItem(Walker proprietary) {
        if(proprietary.isAbleLearnEarthSpell()){
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        }
    }
}
