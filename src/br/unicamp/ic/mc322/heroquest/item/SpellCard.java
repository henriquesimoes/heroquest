package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.skills.Skill;

public abstract class SpellCard extends HeapItem {
    protected Skill spell;

    protected SpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
    }
}
