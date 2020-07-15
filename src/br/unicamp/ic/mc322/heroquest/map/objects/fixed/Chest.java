package br.unicamp.ic.mc322.heroquest.map.objects.fixed;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.artifacts.GoldCoin;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Chest extends FixedObject {
    private boolean opened;
    private ArrayList<CollectableItem> items;

    public Chest(Coordinate position) {
        opened = false;
        items = new ArrayList<>();
        setPosition(position);

        addRandomQuantityOfGold();
        addRandomWeapons();
        addRandomArmor();
    }

    public boolean isOpened() {
        return opened;
    }

    public ArrayList<CollectableItem> getItems() {
        return items;
    }

    @Override
    public void interact(Walker agent) {
        /**
         * TODO: Implement opening interaction with chest
         *
         * Walker might not be able to use the chest weapon. It must be treated during interaction.
         */

        return;
    }

    private void addRandomQuantityOfGold() {
        int totalInGoldInsideChest = Randomizer.randInt(20, 100);

        GoldCoin coins = new GoldCoin(totalInGoldInsideChest);

        items.add(coins);
    }

    private void addRandomWeapons() {
        int weaponsQuantity = Randomizer.nextInt(5);

        for (int i = 0; i < weaponsQuantity; i++) {
            Weapon randomWeapon = Weapon.getRandomWeapon();
            items.add(randomWeapon);
        }
    }

    private void addRandomArmor() {
        if (willHaveAnArmorInside())
            items.add(Armor.getRandomArmor());
    }

    private boolean willHaveAnArmorInside() {
        return Randomizer.nextBoolean();
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
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
