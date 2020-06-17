package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.armors.Armor;
import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.Fists;
import br.unicamp.ic.mc322.heroquest.loop.GameMonitor;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

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
    protected int maxBodyPoints, currentBodyPoints, mindPoints;
    protected HashMap<Skill, Integer> skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;
    protected WalkerManager walkerManager;
    protected boolean ableLearnFireSpell, ableLearnAirSpell, ableLearnEarthSpell, ableLearnWaterSpell;

    public Walker() {
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new HashMap<>();
        movementDice = 2;

        //Add skill of attack with the fists
        Weapon fists = new Fists();
        addSkill(fists.getSkills().get(0));
    }

    public String getStatus(){
        String status = String.format("Name: %s\n",  name);
        status += String.format("Life: %d/%d\n", currentBodyPoints, maxBodyPoints);
        status += String.format("Armor: %s\n", (armor == null? "none" : armor.getItemName()));
        if (leftWeapon != null && leftWeapon.isTwoHanded())
            status += String.format("Weapon: %s\n", leftWeapon.getItemName());
        else {
            status += String.format("Left Weapon: %s\n", (leftWeapon == null ? "none" : leftWeapon.getItemName()));
            status += String.format("Right Weapon: %s\n", (rightWeapon == null ? "none" : rightWeapon.getItemName()));
        }
        return  status;
    }

    public WalkerManager getManager(){
        return walkerManager;
    }

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> skillsList = new ArrayList<>();

        for (Map.Entry<Skill, Integer> pair : skills.entrySet()) {
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

    public void notifyDamage(int damage){
        GameMonitor gameMonitor = GameMonitor.getInstance();
        gameMonitor.notifyDamage(this, damage);
    }

    public void notifyIfIsDead(){
        if (!isAlive()){
            GameMonitor gameMonitor = GameMonitor.getInstance();
            gameMonitor.notifyDeath(this);
        }
    }

    public void defendsSkill(int intensityAttack, int intensityDefence){
        int damage = Math.max(intensityAttack - intensityDefence, 0);
        decreaseBodyPoints(damage);
        notifyDamage(damage);
        notifyIfIsDead();
    }

    public void defendsMagicSkill(int intensityAttack) {
        int intensityDefence = getIntensityDefense(mindPoints);
        defendsSkill(intensityAttack, intensityDefence);
    }

    public void defendsPhysicalSkill(int intensityAttack) {
        int intensityDefence = getIntensityDefense(defenseDice + bonusDefenseDice);
        defendsSkill(intensityAttack, intensityDefence);
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

        for (PhysicalSkill skill : skills)
            addSkill(skill);
    }

    private void unequipWeapon(Weapon weapon) {
        knapsack.put(weapon);

        ArrayList<PhysicalSkill> skills = weapon.getSkills();

        for (PhysicalSkill skill : skills)
            removeSkill(skill);
    }

    public void addSkill(Skill skill) {
        Integer amount = skills.get(skill);

        if (amount == null)
            skills.put(skill, 1);
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
