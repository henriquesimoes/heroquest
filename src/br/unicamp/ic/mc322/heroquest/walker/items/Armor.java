package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.armors.ChainMail;
import br.unicamp.ic.mc322.heroquest.walker.items.armors.CloakOfProtection;

public abstract class Armor extends DurableItem {
    private final int defenseBonus;

    protected Armor(String itemName, String itemDescription, int defenseBonus, int durability, int goldCoinsValue, DurableItemClass durableItemClass) {
        super(itemName, itemDescription, durability, goldCoinsValue, durableItemClass);
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
        if (proprietary.isAbleToUse(durableItemClass))
            proprietary.equipArmor(this);
        else
            proprietary.getManager().showMessage("You are not able to use this armor");
    }
}
