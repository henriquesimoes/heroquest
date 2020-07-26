package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;
import br.unicamp.ic.mc322.heroquest.walker.items.Armor;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Item;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.Fists;
import br.unicamp.ic.mc322.heroquest.walker.skills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

import java.util.*;

public abstract class Walker implements MapObject {
    protected Team team;
    protected String name;
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int attackDice, movementDice, defenseDice, bonusDefenseDice;
    protected int maximumBodyPoints, currentBodyPoints, mindPoints;
    protected java.util.Map<Skill, Integer> skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;
    protected WalkerManager walkerManager;
    protected Collection<SpellElement> spellsAbleToLearn;
    protected Collection<DurableItemClass> durableItemsAbleToUse;
    private Coordinate position;
    private int balance;

    public Walker(WalkerManager manager, String name) {
        this.name = name;
        this.walkerManager = manager;
        this.walkerManager.setWalker(this);
        this.balance = 1000;

        position = null;
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new TreeMap<>();
        spellsAbleToLearn = new ArrayList<>();
        durableItemsAbleToUse = new ArrayList<>(Arrays.asList(DurableItemClass.NEUTRAL));
        movementDice = 2;

        // Add fists attack skill
        Weapon fists = new Fists();
        addSkill(fists.getSkills().get(0));
    }

    /**
     * @return the maximum number of movements the walker
     * can currently do for walking.
     */
    public int getPositionLimitInMovement() {
        int numPos = 0;

        for (int i = 0; i < movementDice; i++)
            numPos += redDice.roll();

        return numPos;
    }

    public int getPhysicalAttackPower(Weapon weapon) {
        int attackIntensity = 0;
        int totalAttack = attackDice + weapon.getAttackBonus();

        for (int times = 0; times < totalAttack; times++)
            if (combatDice.roll() == CombatDiceFace.SKULL)
                attackIntensity++;

        return attackIntensity;
    }

    public boolean attemptMagicalMovement() {
        return redDice.roll() <= mindPoints;
    }

    public void restoreBodyPoints(int delta) {
        currentBodyPoints = Math.min(currentBodyPoints + delta, maximumBodyPoints);
    }

    public void decreaseBodyPoints(int delta) {
        currentBodyPoints = Math.max(currentBodyPoints - delta, 0);
        notifyDamage(delta);
        notifyIfIsDead();
    }

    private void defendFromSkill(int attackIntensity, int defenseIntensity) {
        int damage = Math.max(attackIntensity - defenseIntensity, 0);
        decreaseBodyPoints(damage);
    }

    private int getDefenseIntensity(int numberOfDices) {
        int intensity = 0;

        for (int times = 0; times < numberOfDices; times++)
            if (combatDice.roll() == getTeam().getFavorableDiceFace())
                intensity++;

        return intensity;
    }

    public void defendFromMagicSkill(int attackIntensity) {
        int defenseIntensity = getDefenseIntensity(mindPoints);
        defendFromSkill(attackIntensity, defenseIntensity);
    }

    public void defendsPhysicalSkill(int attackIntensity) {
        int defenseIntensity = getDefenseIntensity(defenseDice + bonusDefenseDice);
        defendFromSkill(attackIntensity, defenseIntensity);
        if (armor != null)
            armor.degradeByUse(this);
    }

    private void notifyDamage(int damage) {
        GameMonitor gameMonitor = GameMonitor.getInstance();
        gameMonitor.notifyDamage(this, damage);
    }

    private void notifyIfIsDead() {
        if (!isAlive()) {
            GameMonitor gameMonitor = GameMonitor.getInstance();
            gameMonitor.notifyDeath(this);
        }
    }

    public void increaseBalance(int amount) {
        walkerManager.showMessage("Obtained " + amount + " gold coin(s)");
        balance += amount;
    }

    public void collectItem(Item item) {
        walkerManager.showMessage("Collected the item: " + item.getName());
        knapsack.put(item);
    }

    /**
     * Erases the given item from the inventory
     *
     * @param item - item to be removed
     */
    public void destroyItem(Item item) {
        if (leftWeapon != null && leftWeapon.equals(item))
            storeLeftWeapon();

        if (rightWeapon != null && rightWeapon.equals(item))
            storeRightWeapon();

        if (armor != null && armor.equals(item))
            unequipArmor();

        knapsack.remove(item);
    }

    public void addSkill(Skill skill) {
        Integer amount = skills.get(skill);

        if (amount == null) {
            skills.put(skill, 1);
            skill.setWalkerManager(walkerManager);
        } else
            skills.replace(skill, amount + 1);
    }

    public void removeSkill(Skill skill) {
        Integer amount = skills.get(skill);

        if (amount == -1)
            throw new NoSuchElementException();

        if (amount == 1)
            skills.remove(skill);
        else
            skills.replace(skill, amount - 1);
    }

    public void equipWeapon(Weapon weapon) {
        knapsack.remove(weapon);
        String suffixDescription = "";

        if (weapon.isTwoHanded()) {
            storeLeftWeapon();
            storeRightWeapon();
            leftWeapon = weapon;
        } else {
            if (rightWeapon == null) {
                rightWeapon = weapon;
                suffixDescription = " - Right Hand";
            } else {
                storeLeftWeapon();
                leftWeapon = weapon;
                suffixDescription = " - Left Hand";
            }
        }

        for (PhysicalSkill skill : weapon.getSkills()) {
            skill.setNameSuffix(suffixDescription);
            addSkill(skill);
        }
    }

    private void unequipWeapon(Weapon weapon) {
        knapsack.put(weapon);

        for (PhysicalSkill skill : weapon.getSkills()) {
            skill.clearNameSuffix();
            removeSkill(skill);
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

    public void equipArmor(Armor nextArmor) {
        if (armor != null)
            unequipArmor();

        armor = nextArmor;
        bonusDefenseDice += nextArmor.getDefenseBonus();
    }

    protected void unequipArmor() {
        if (armor != null) {
            knapsack.put(armor);
            armor = null;
            bonusDefenseDice -= armor.getDefenseBonus();
        }
    }

    protected String getStatus() {
        String status = String.format("Name: %s\n", name);
        status += String.format("Life: %d/%d\n", currentBodyPoints, maximumBodyPoints);
        status += String.format("Armor: %s\n", (armor == null ? "none" : armor.representationOnStatus()));
        if (leftWeapon != null && leftWeapon.isTwoHanded())
            status += String.format("Weapon: %s\n", leftWeapon.representationOnStatus());
        else {
            status += String.format("Left Weapon: %s\n", (leftWeapon == null ? "none" : leftWeapon.representationOnStatus()));
            status += String.format("Right Weapon: %s\n", (rightWeapon == null ? "none" : rightWeapon.representationOnStatus()));
        }
        return status;
    }

    public Skill[] getSkillList() {
        return skills.keySet().toArray(new Skill[0]);
    }

    public WalkerManager getManager() {
        return walkerManager;
    }

    protected String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public Item[] getItems() {
        return knapsack.getItemList();
    }

    protected boolean isAlive() {
        return currentBodyPoints > 0;
    }

    public int rollRedDice() {
        return redDice.roll();
    }

    public boolean isEnemy(Walker walker) {
        return this.team != walker.team;
    }

    public boolean isFriend(Walker walker) {
        return this.team == walker.team;
    }

    public boolean isAbleToLearn(SpellElement spellElement) {
        return spellsAbleToLearn.contains(spellElement);
    }

    public boolean isAbleToUse(DurableItemClass durableItemClass) {
        return durableItemsAbleToUse.contains(durableItemClass);
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }

    public void setMap(Map map) {
        walkerManager.setMap(map);
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coordinate position) {
        if (this.position == null)
            this.position = new Coordinate(position.getX(), position.getY());
        else
            this.position.copy(position);
    }

    public String getAttributeList() {
        String status = getStatus();
        status += "Attack dices: " + attackDice;
        status += (leftWeapon != null ? " + " + leftWeapon.getAttackBonus() : "");
        status += (rightWeapon != null ? " + " + rightWeapon.getAttackBonus() : "") + "\n";
        status += "Defence dices: " + defenseDice + (bonusDefenseDice != 0 ? (" + " + bonusDefenseDice) : "") + "\n";
        status += "Mind points: " + mindPoints + "\n";

        status += "Class of durable items able to use: ";
        for (DurableItemClass durableItemClass : durableItemsAbleToUse)
            status += durableItemClass.toString() + ", ";
        status = status.substring(0, status.length() - 2) + "\n";


        status += "Elements of spells able to learn: ";
        if (spellsAbleToLearn.size() == 0)
            status += "none\n";
        else {
            for (SpellElement spellElement : spellsAbleToLearn)
                status += spellElement.toString() + ", ";
            status = status.substring(0, status.length() - 2) + "\n";
        }

        status += "Balance: " + balance + " gold coin(s)" + "\n";
        return status;
    }

    public java.util.Map<Item, Integer> getInventory() {
        return knapsack.getItems();
    }

    public java.util.Map<Skill, Integer> getSkills() {
        return skills;
    }
}
