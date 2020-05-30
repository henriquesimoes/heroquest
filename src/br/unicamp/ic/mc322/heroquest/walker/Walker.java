package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.skills.Skill;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;

public abstract class Walker extends MapObject {
    protected Team team;
    protected String name;
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int attackDice, moveDice, defenseDice, bonusDefenseDice;
    protected int maxBodyPoints, currentBodyPoints, mindPoints;
    protected ArrayList<Pair<Skill, Integer>> skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;

    public Walker() {
        super();
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new ArrayList<>();
        moveDice = 2;
    }

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> skillsList = new ArrayList<>();

        for (Pair<Skill, Integer> pair : skills) {
            Skill skill = pair.getKey();
            skillsList.add(skill);
        }

        return skillsList;
    }

    public int getLimitPositionInMovement(){
        int numPos = 0;

        for(int i = 0; i < moveDice; i++)
            numPos += redDice.rollIndex();

        return numPos;
    }

    public int getPowerPhysicalAttack(Weapon weapon) {
        int intensity = 0;
        int totalAttack = attackDice + weapon.getAttackBonus();

        for (int times = 0; times < totalAttack; times++)
            if (combatDice.roll() == CombatDiceFace.SKULL)
                intensity++;

        return intensity;
    }

    private boolean tryUseMagicalMovement() {
        return redDice.rollIndex() <= mindPoints;
    }

    public abstract int getIntensityOfPhysicalDefense();

    public void defendsMagicSkill(int intensity){
        boolean sucessDefend = tryUseMagicalMovement();
        if(!sucessDefend)
            decreaseBodyPoints(intensity);
    }

    public void defendsPhysicalSkill(int intensity){
        int intensityDefence = getIntensityOfPhysicalDefense();
        if(intensityDefence < intensity)
            decreaseBodyPoints(intensity - intensityDefence);
    }

    public boolean isAlive() {
        return currentBodyPoints > 0;
    }

    // erase the item of the inventory
    private void destroyItem(CollectableItem item) {
        if (leftWeapon != null && leftWeapon.equals(item))
            unequipWeapon((Weapon) item);

        if (rightWeapon != null && rightWeapon.equals(item))
            unequipWeapon((Weapon) item);

        if (armor != null && armor.equals(item))
            unequipArmor();

        knapsack.remove(item);
    }

    private void restoreBodyPoints(int delta) {
        currentBodyPoints = Math.min(currentBodyPoints + delta, maxBodyPoints);
    }

    private void decreaseBodyPoints(int delta) {
        currentBodyPoints = Math.max(currentBodyPoints - delta, 0);
    }

    protected void equipWeapon(Weapon weapon) {
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

    protected void addSkill(Skill skill){
        // TODO: test if this really work
        int index = skills.indexOf(new Pair<Skill, Integer>(skill, 0));

        if (index == -1) {
            skills.add(new Pair<>(skill, 1));
        } else {
            Pair<Skill, Integer> pair = skills.get(index);
            pair.setValue(pair.getValue() + 1);
        }
    }

    private void removeSkill(Skill skill){
        // TODO: test if this really work
        int index = skills.indexOf(new Pair<Skill, Integer>(skill, 0));

        if (index == -1) {
            System.out.println("Fatal Error");
            System.exit(1);
        } else {
            Pair<Skill, Integer> pair = skills.get(index);

            if (pair.getValue() == 1)
                skills.remove(index);
            else
                pair.setValue(pair.getValue() - 1);
        }
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

    protected void equipArmor(Armor nextArmor) {
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
    public boolean isWalkOverable() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        return;
    }

    public boolean isEnemy(Walker walker){
        return this.team != walker.team;
    }

    public boolean isFriend(Walker walker){
        return this.team == walker.team;
    }
}
