package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Fists;
import br.unicamp.ic.mc322.heroquest.loop.GameMonitor;
import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;

import java.util.HashMap;
import java.util.NoSuchElementException;

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
    protected boolean ableToLearnFireSpell, ableToLearnAirSpell, ableToLearnEarthSpell, ableToLearnWaterSpell;
    private Coordinate position;
    private int balance;


    public Walker(WalkerManager manager, String name) {
        this.name = name;
        this.walkerManager = manager;
        this.walkerManager.setWalker(this);
        this.balance = 1000;

        position = new Coordinate();
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new HashMap<>();
        movementDice = 2;

        // Add fists attack skill
        Weapon fists = new Fists();
        addSkill(fists.getSkills().get(0));
    }

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

    protected abstract int getDefenseIntensity(int numberOfDices);

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

    public void collectItem(CollectableItem item) {
        walkerManager.showMessage("Collected the item: " + item.getItemName());
        knapsack.put(item);
    }

    /**
     * Erases the given item from the inventory
     *
     * @param item item to be removed
     */
    public void destroyItem(CollectableItem item) {
        if (leftWeapon != null && leftWeapon.equals(item))
            unequipWeapon((Weapon) item);

        if (rightWeapon != null && rightWeapon.equals(item))
            unequipWeapon((Weapon) item);

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

        if (weapon.isTwoHanded()) {
            storeLeftWeapon();
            storeRightWeapon();
            leftWeapon = weapon;
        } else {
            if (rightWeapon == null)
                rightWeapon = weapon;
            else {
                storeLeftWeapon();
                leftWeapon = weapon;
            }
        }

        for (Skill skill : weapon.getSkills())
            addSkill(skill);
    }

    private void unequipWeapon(Weapon weapon) {
        knapsack.put(weapon);

        for (Skill skill : weapon.getSkills())
            removeSkill(skill);
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
            bonusDefenseDice -= armor.getDefenseBonus();
        }
    }

    protected String getStatus() {
        String status = String.format("Name: %s\n", name);
        status += String.format("Life: %d/%d\n", currentBodyPoints, maximumBodyPoints);
        status += String.format("Armor: %s\n", (armor == null ? "none" : armor.getItemName()));
        if (leftWeapon != null && leftWeapon.isTwoHanded())
            status += String.format("Weapon: %s\n", leftWeapon.getItemName());
        else {
            status += String.format("Left Weapon: %s\n", (leftWeapon == null ? "none" : leftWeapon.getItemName()));
            status += String.format("Right Weapon: %s\n", (rightWeapon == null ? "none" : rightWeapon.getItemName()));
        }
        return status;
    }

    public Skill[] getSkills() {
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

    public CollectableItem[] getItems() {
        return knapsack.getItems();
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

    public boolean isAbleToLearnFireSpell() {
        return ableToLearnFireSpell;
    }

    public boolean isAbleToLearnAirSpell() {
        return ableToLearnAirSpell;
    }

    public boolean isAbleToLearnEarthSpell() {
        return ableToLearnEarthSpell;
    }

    public boolean isAbleToLearnWaterSpell() {
        return ableToLearnWaterSpell;
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

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position.copyValue(position);
    }
}
