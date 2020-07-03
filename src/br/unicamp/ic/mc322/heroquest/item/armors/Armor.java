package br.unicamp.ic.mc322.heroquest.item.armors;

import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Armor extends DurableItem {
    private int defenseBonus;
    public Armor(String itemName, String itemDescription, int defenseBonus, int durability, int goldCoinsValue) {
        super(itemName, itemDescription, durability, goldCoinsValue);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void useItem(Walker proprietary){
        proprietary.equipArmor(this);
    }
}
