package br.unicamp.ic.mc322.heroquest.map.object.fixed;

import br.unicamp.ic.mc322.heroquest.item.armors.Armor;
import br.unicamp.ic.mc322.heroquest.item.artifacts.GoldCoin;
import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Chest extends FixedObject {
    private boolean opened;
    private ArrayList<CollectableItem> itemsInsideChest = new ArrayList<>();

    public Chest() {
        opened = false;

        addRandomQuantityOfGoldToChest();
        addRandomWeaponsToChest();
        addRandomArmorToChest();
    }

    private void addRandomQuantityOfGoldToChest() {
        int totalInGoldInsideChest = Randomizer.randInt(20, 100);

        GoldCoin coins = new GoldCoin(totalInGoldInsideChest);

        itemsInsideChest.add(coins);
    }

    /**TODO: Personagem pode não ser capaz de usar uma determinada arma.
     * Tratar caso, se necessário, no momento de interação.*/

    private void addRandomWeaponsToChest() {
        int weaponsQuantity = Randomizer.nextInt(5);

        for (int i = 0; i < weaponsQuantity; i++) {
            Weapon randomWeapon = Weapon.getRandomWeapon();
            itemsInsideChest.add(randomWeapon);
        }
    }

    private void addRandomArmorToChest() {
        if (willHaveAnArmorInside())
            itemsInsideChest.add(Armor.getRandomArmor());
    }

    private boolean willHaveAnArmorInside() {
        return Randomizer.nextBoolean();
    }

    public boolean isOpened() {
        return opened;
    }

    public ArrayList<CollectableItem> getItemsInsideChest() {
        return itemsInsideChest;
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        /**
         * TODO: Implement opening interaction with chest
         */
        return;
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Chest on " + getPosition();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
