package br.unicamp.ic.mc322.heroquest.map.objects.fixed;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.artifacts.GoldCoin;
import br.unicamp.ic.mc322.heroquest.item.potions.HealthPotion;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Chest extends FixedObject {
    private final int MINIMUM_GOLD_QUANTITY = 20;
    private final int MAXIMUM_GOLD_QUANTITY = 100;
    private final int MAXIMUM_WEAPONS_QUANTITY = 3;
    private final int MAXIMUM_HEALTH_POTIONS_QUANTITY = 3;
    private final int MAXIMUM_SPELL_CARD_QUANTITY = 3;
    private boolean opened;
    private final ArrayList<CollectableItem> items;
    private GoldCoin coins;

    public Chest(Coordinate position) {
        opened = false;
        items = new ArrayList<>();
        setPosition(position);

        addRandomQuantityOfGold();
        addRandomWeapons();
        addRandomSpellCards();
        addHealthPotions();
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
        if (opened) {
            for (CollectableItem item : items)
                agent.collectItem(item);
            coins.useItem(agent);
        } else
            opened = true;
    }

    private void addRandomQuantityOfGold() {
        int totalInGoldInsideChest = Randomizer.randInt(MINIMUM_GOLD_QUANTITY, MAXIMUM_GOLD_QUANTITY);

        coins = new GoldCoin(totalInGoldInsideChest);
    }

    private void addRandomWeapons() {
        int weaponsQuantity = Randomizer.randInt(0, MAXIMUM_WEAPONS_QUANTITY);

        for (int i = 0; i < weaponsQuantity; i++)
            items.add(Weapon.getRandomWeapon());
    }

    private void addRandomSpellCards() {
        int spellCardQuantity = Randomizer.randInt(0, MAXIMUM_SPELL_CARD_QUANTITY);

        for (int i = 0; i < spellCardQuantity; i++)
            items.add(SpellCard.getRandomSpellCard());
    }

    private void addHealthPotions() {
        int healthPotionsQuantity = Randomizer.randInt(0, MAXIMUM_HEALTH_POTIONS_QUANTITY);

        for (int i = 0; i < healthPotionsQuantity; i++)
            items.add(new HealthPotion());
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
        if (opened)
            return "Opened chest on " + getPosition();
        else
            return "Closed chest on " + getPosition();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
