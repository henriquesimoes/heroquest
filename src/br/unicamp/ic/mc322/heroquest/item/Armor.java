package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.item.armors.ChainMail;
import br.unicamp.ic.mc322.heroquest.item.armors.CloakOfProtection;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Armor extends DurableItem {
    private int defenseBonus;

    protected Armor(String itemName, String itemDescription, int defenseBonus, int durability, int goldCoinsValue, ItemClass itemClass) {
        super(itemName, itemDescription, durability, goldCoinsValue, itemClass);
        this.defenseBonus = defenseBonus;
    }

    public static Armor getRandomArmor() {
        Armor[] possibleArmors = {
                new CloakOfProtection(),
                new ChainMail()
        };

        int choice = Randomizer.nextInt(possibleArmors.length);

        return possibleArmors[choice];
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToUse(itemClass))
            proprietary.equipArmor(this);
        else
            proprietary.getManager().showMessage("You are not able to use this armor");
    }
}
