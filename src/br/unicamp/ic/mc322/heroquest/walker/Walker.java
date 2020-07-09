package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Fists;
import br.unicamp.ic.mc322.heroquest.loop.GameMonitor;
import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapUnit;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class Walker extends MapObject {
    protected Team team;
    protected String name;
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int attackDice, movementDice, defenseDice, bonusDefenseDice;
    protected int maximumBodyPoints, currentBodyPoints, mindPoints;
    protected HashMap<Skill, Integer> skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;
    protected WalkerManager walkerManager;
    protected boolean ableToLearnFireSpell, ableToLearnAirSpell, ableToLearnEarthSpell, ableToLearnWaterSpell;

    public Walker(WalkerManager manager, String name) {
        this.walkerManager = manager;
        this.name = name;
        this.walkerManager.setWalker(this);

        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new HashMap<>();
        movementDice = 2;

        // Add fists attack skill
        Weapon fists = new Fists();
        addSkill(fists.getSkills().get(0));
    }

    protected String getStatus() {
        String status = String.format("Name: %s\n",  name);
        status += String.format("Life: %d/%d\n", currentBodyPoints, maximumBodyPoints);
        status += String.format("Armor: %s\n", (armor == null? "none" : armor.getItemName()));
        if (leftWeapon != null && leftWeapon.isTwoHanded())
            status += String.format("Weapon: %s\n", leftWeapon.getItemName());
        else {
            status += String.format("Left Weapon: %s\n", (leftWeapon == null ? "none" : leftWeapon.getItemName()));
            status += String.format("Right Weapon: %s\n", (rightWeapon == null ? "none" : rightWeapon.getItemName()));
        }
        return  status;
    }

    public WalkerManager getManager() {
        return walkerManager;
    }

    protected ArrayList<Skill> getSkills() {
        ArrayList<Skill> skillList = new ArrayList<>();

        for (Map.Entry<Skill, Integer> pair : skills.entrySet()) {
            Skill skill = pair.getKey();
            skillList.add(skill);
        }

        return skillList;
    }

    protected int getPositionLimitInMovement() {
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

    public int rollRedDice() {
        return redDice.roll();
    }

    protected abstract int getDefenseIntensity(int numberOfDices);

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

    private void defendFromSkill(int attackIntensity, int defenseIntensity) {
        int damage = Math.max(attackIntensity - defenseIntensity, 0);
        decreaseBodyPoints(damage);
        notifyDamage(damage);
        notifyIfIsDead();
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

    protected boolean isAlive() {
        return currentBodyPoints > 0;
    }

    /**
     * Erases the given item from the inventory
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

    public void restoreBodyPoints(int delta) {
        currentBodyPoints = Math.min(currentBodyPoints + delta, maximumBodyPoints);
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

    public void addSkill(Skill skill) {
        Integer amount = skills.get(skill);

        if (amount == null) {
            skills.put(skill, 1);
            skill.setWalkerManager(walkerManager);
        }
        else
            skills.replace(skill, amount + 1);
    }

    private void removeSkill(Skill skill) {
        Integer amount = skills.get(skill);

        if (amount == -1)
            throw new NoSuchElementException();

        if (amount == 1)
            skills.remove(skill);
        else
            skills.replace(skill, amount - 1);
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

    protected void collectItem(CollectableItem item) {
        knapsack.put(item);
    }

    protected String getName() {
        return name;
    }

    protected ArrayList<CollectableItem> getItems() {
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

    @Override
    public void goTo(MapUnit unit) {
        unit.add(this);
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isEnemy(Walker walker) {
        return this.team != walker.team;
    }

    public boolean isFriend(Walker walker) {
        return this.team == walker.team;
    }

    public Team getTeam() {
        return team;
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
}
