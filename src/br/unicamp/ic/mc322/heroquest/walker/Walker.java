package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.armors.Armor;
import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Walker extends MapObject {
    protected Team team;
    protected String name;
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int attackDice, movementDice, defenseDice, bonusDefenseDice;
    protected int maxBodyPoints, currentBodyPoints, mindPoints;
    protected ArrayList<Pair<Skill, Integer>> skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;
    protected WalkerManager walkerManager;
    protected boolean ableLearnFireSpell, ableLearnAirSpell, ableLearnEarthSpell, ableLearnWaterSpell;

    public Walker() {
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new ArrayList<>();
        movementDice = 2;
    }

    public WalkerManager getManager(){
        return walkerManager;
    }

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> skillsList = new ArrayList<>();

        for (Pair<Skill, Integer> pair : skills) {
            Skill skill = pair.getKey();
            skillsList.add(skill);
        }

        return skillsList;
    }

    public int getPositionLimitInMovement() {
        int numPos = 0;

        for (int i = 0; i < movementDice; i++)
            numPos += redDice.roll();

        return numPos;
    }

    public int getPhysicalAttackPower(Weapon weapon) {
        int intensity = 0;
        int totalAttack = attackDice + weapon.getAttackBonus();

        for (int times = 0; times < totalAttack; times++)
            if (combatDice.roll() == CombatDiceFace.SKULL)
                intensity++;

        return intensity;
    }

    public boolean attemptMagicalMovement() {
        return redDice.roll() <= mindPoints;
    }

    public int rollRedDice() {
        return redDice.roll();
    }

    public abstract int getIntensityDefense(int numberOfDices);

    public void defendsMagicSkill(int intensity) {
        int intensityDefence = getIntensityDefense(mindPoints);
        if (intensityDefence < intensity)
            decreaseBodyPoints(intensity - intensityDefence);
    }

    public void defendsPhysicalSkill(int intensity) {
        int intensityDefence = getIntensityDefense(defenseDice + bonusDefenseDice);
        if (intensityDefence < intensity)
            decreaseBodyPoints(intensity - intensityDefence);
        if (armor != null)
            armor.degradeByUse(this);
    }

    public boolean isAlive() {
        return currentBodyPoints > 0;
    }

    // erase the item of the inventory
    public void destroyItem(CollectableItem item) {
        if (leftWeapon != null && leftWeapon.equals(item))
            unequipWeapon((Weapon) item);

        if (rightWeapon != null && rightWeapon.equals(item))
            unequipWeapon((Weapon) item);

        if (armor != null && armor.equals(item))
            unequipArmor();

        knapsack.remove(item);
    }

    public void restoreBodyPoints(int delta) {
        currentBodyPoints = Math.min(currentBodyPoints + delta, maxBodyPoints);
    }

    private void decreaseBodyPoints(int delta) {
        currentBodyPoints = Math.max(currentBodyPoints - delta, 0);
    }

    public void equipWeapon(Weapon weapon) {
        knapsack.remove(weapon);

        if (weapon.isTwoHanded()) {
            storeLeftWeapon();
            storeRightWeapon();
            leftWeapon = weapon;

        } else {
            if (rightWeapon == null) {
                rightWeapon = weapon;

            } else {
                storeLeftWeapon();
                leftWeapon = weapon;
            }
        }

        ArrayList<PhysicalSkill> skills = weapon.getSkills();

        for (PhysicalSkill skill : skills) {
            addSkill(skill);
        }
    }

    private void unequipWeapon(Weapon weapon) {
        knapsack.put(weapon);

        ArrayList<PhysicalSkill> skills = weapon.getSkills();

        for (PhysicalSkill skill : skills) {
            removeSkill(skill);
        }
    }

    public void addSkill(Skill skill) {
        // TODO: test if this really works
        int index = skills.indexOf(new Pair<Skill, Integer>(skill, 0));

        if (index == -1) {
            skills.add(new Pair<>(skill, 1));
        } else {
            Pair<Skill, Integer> pair = skills.get(index);
            pair.setValue(pair.getValue() + 1);
        }
    }

    private void removeSkill(Skill skill) {
        // TODO: test if this really works
        int index = skills.indexOf(new Pair<Skill, Integer>(skill, 0));

        if (index == -1)
            throw new NoSuchElementException();

        Pair<Skill, Integer> pair = skills.get(index);

        if (pair.getValue() == 1)
            skills.remove(index);
        else
            pair.setValue(pair.getValue() - 1);
    }

    protected void storeLeftWeapon() {
        if (leftWeapon != null) {
            unequipWeapon(leftWeapon);
            leftWeapon = null;
        }
    }

    protected void storeRightWeapon() {
        if (rightWeapon != null) {
            unequipWeapon(rightWeapon);
            rightWeapon = null;
        }
    }

    public void equipArmor(Armor nextArmor) {
        if (armor != null)
            unequipArmor();

        armor = nextArmor;
        bonusDefenseDice += nextArmor.getDefenceBonus();
    }

    protected void unequipArmor() {
        if (armor != null) {
            knapsack.put(armor);
            bonusDefenseDice -= armor.getDefenceBonus();
        }
    }

    protected void collectItem(CollectableItem item) {
        knapsack.put(item);
    }

    public String getName() {
        return name;
    }

    public ArrayList<CollectableItem> getItems() {
        return knapsack.getItems();
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        return;
    }

    public boolean isEnemy(Walker walker) {
        return this.team != walker.team;
    }

    public boolean isFriend(Walker walker) {
        return this.team == walker.team;
    }

    /**TODO: Classe com implementação vazia até decidirmos formato do mapa*/
    public ArrayList<Coordinate> getPositionsInEntitySight() { return new ArrayList<>();}

    public boolean isAbleLearnFireSpell(){
        return ableLearnFireSpell;
    }

    public boolean isAbleLearnAirSpell(){
        return ableLearnAirSpell;
    }

    public boolean isAbleLearnEarthSpell(){
        return ableLearnEarthSpell;
    }

    public boolean isAbleLearnWaterSpell(){
        return ableLearnWaterSpell;
    }
}
