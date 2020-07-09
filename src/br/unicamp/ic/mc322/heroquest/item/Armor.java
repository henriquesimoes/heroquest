package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.item.armors.ChainMail;
import br.unicamp.ic.mc322.heroquest.item.armors.CloakOfProtection;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Armor extends DurableItem {
    private int defenseBonus;

    public Armor(String itemName, String itemDescription, int defenseBonus, int durability, int goldCoinsValue) {
        super(itemName, itemDescription, durability, goldCoinsValue);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void useItem(Walker proprietary) {
        proprietary.equipArmor(this);
    }

    public static Armor getRandomArmor() {
        Armor[] possibleArmors = {
                new CloakOfProtection(),
                new ChainMail()
        };

        int choice = Randomizer.randInt(0, possibleArmors.length - 1);

        return possibleArmors[choice];
    }
}
