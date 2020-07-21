package br.unicamp.ic.mc322.heroquest.map.objects.fixed;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.Item;
import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.artifacts.GoldCoin;
import br.unicamp.ic.mc322.heroquest.item.potions.HealthPotion;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.MonsterGenerator;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Chest extends FixedObject {
    private static final int MINIMUM_GOLD_QUANTITY = 20;
    private static final int MAXIMUM_GOLD_QUANTITY = 100;
    private static final int MAXIMUM_WEAPONS_QUANTITY = 3;
    private static final int MAXIMUM_HEALTH_POTIONS_QUANTITY = 3;
    private static final int MAXIMUM_SPELL_CARD_QUANTITY = 3;
    private static final double PROBABILITY_OF_APPEARING_AN_ARMOR = .3;
    private static final double PROBABILITY_OF_APPEARING_A_MONSTER = 0.3;
    private boolean opened;
    private ArrayList<Item> items;
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

    @Override
    public void interact(Walker agent) {
        if (opened) {
            for (Item item : items)
                agent.collectItem(item);
            items.clear();

            coins.useItem(agent);
            coins = null;
        } else {
            opened = true;
            if (PROBABILITY_OF_APPEARING_A_MONSTER >= Randomizer.nextDouble()) {
                // Maybe there is not an empty position for the monster to appear; in this case, nothing happens
                if (MonsterGenerator.appearMonsterClose(agent, this.getPosition()))
                    agent.getManager().showMessage("A monster appeared");
            }
        }
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
        return PROBABILITY_OF_APPEARING_AN_ARMOR >= Randomizer.nextDouble();
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
