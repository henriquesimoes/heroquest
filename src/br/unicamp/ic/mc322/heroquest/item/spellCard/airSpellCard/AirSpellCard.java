package br.unicamp.ic.mc322.heroquest.item.spellCard.airSpellCard;

import br.unicamp.ic.mc322.heroquest.item.spellCard.SpellCard;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class AirSpellCard extends SpellCard {
    public AirSpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription + "Can be used only if character is able to learn air spells.", goldCoinsValue);
    }

    public void useItem(Walker proprietary) {
        if(proprietary.isAbleToLearnAirSpell()){
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        }
    }
}
