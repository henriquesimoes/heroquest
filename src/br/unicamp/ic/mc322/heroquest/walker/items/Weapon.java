package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.*;
import br.unicamp.ic.mc322.heroquest.walker.skills.PhysicalSkill;

import java.util.ArrayList;

public abstract class Weapon extends DurableItem {
    private int attackBonus;
    private boolean attackDiagonally;
    private boolean twoHanded;
    private ArrayList<PhysicalSkill> itemSkills = new ArrayList<>();

    protected Weapon(String name, String description, int weaponDurability, int goldCoinsValue, ItemClass itemClass) {
        super(name, description, weaponDurability, goldCoinsValue, itemClass);
    }

    public static Weapon getRandomWeapon() {
        Weapon[] possibleWeapons = {
                new BattleAxe(),
                new Crossbow(),
                new Dagger(),
                new Flail(),
                new LongSword(),
                new ShortSword(),
                new Staff(),
        };

        int choice = Randomizer.randInt(0, possibleWeapons.length - 1);

        return possibleWeapons[choice];
    }

    public ArrayList<PhysicalSkill> getSkills() {
        return itemSkills;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    protected void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public boolean canAttackDiagonally() {
        return attackDiagonally;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    protected void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    @Override
    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToUse(itemClass))
            proprietary.equipWeapon(this);
        else
            proprietary.getManager().showMessage("You are not able to use this weapon");
    }

    protected void setAttackDiagonally(boolean attackDiagonally) {
        this.attackDiagonally = attackDiagonally;
    }

    protected void addSkill(PhysicalSkill skill) {
        itemSkills.add(skill);
    }
}
