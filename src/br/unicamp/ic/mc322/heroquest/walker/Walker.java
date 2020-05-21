package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.skill.Skill;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;

import java.util.LinkedHashMap;

public abstract class Walker {
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int maxBodyPoints, curBodyPoints, mindPoints, attackDice, moveDice, defenseDice, bonusAttackDice, bonusDefenseDice;
    protected LinkedHashMap <Skill, Integer > skills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Knapsack knapsack;

    Walker(){
        redDice = new RedDice();
        combatDice = new CombatDice();
        knapsack = new Knapsack();
        skills = new LinkedHashMap <>();
        moveDice = 2;
    }

    private void useItemOfSkill(Skill skill){
        CollectableItem item = skill.getItem();
        item.use();
        if(item.isDestroy())
            destroyItem(item);
    }

    public void executeAction(Action action, int intensity){
        switch (action){
            case DAMAGE:
                decreaseBodyPoints(intensity);
                break;
            case HEALING:
                restoreBodyPoints(intensity);
                break;
        }
    }

    public boolean isAlive(){
        return curBodyPoints > 0;
    }

    public int getIntensitySkill(Skill skill){
        switch (skill.getAction()){
            case HEALING:
                // TODO: implement amount of points of life restored
                break;
            case DAMAGE:
                // TODO: implement amount of damage
            default:
                //fatal error;
                break;
        }
        return 0; // only for the compiler not complain
    }

    /**
     * @param skill
     * @return if the skill was executed with success
     */
    public boolean tryUseSkill(Skill skill){

        useItemOfSkill(skill);

        switch(skill.getType()){
            case MAGIC:
                // TODO: implement chance of magic skill are used with success
                break;
            case PHYSICAL:
                // TODO: implement chance of magic skill are used with success
                break;
            default:
                //fatal error;
                break;
        }

        return false; // only for the compiler not complain
    }

    public boolean skillHasTarget(Skill skill){
        switch (skill.getAction()){
            case DAMAGE:
                return true;
            case HEALING:
                return false;
        }
        return false; // only for the compiler not complain
    }

    // erase the item of the inventory
    private void destroyItem(CollectableItem item){
        if (leftWeapon != null && leftWeapon.equals(item))
            unequipWeapon((Weapon)item);
        if (rightWeapon != null && rightWeapon.equals(item))
            unequipWeapon((Weapon)item);
        if (armor != null && armor.equals(item))
            unequipArmor();
        knapsack.remove(item);
    }

    private void restoreBodyPoints(int delta){
        curBodyPoints = Math.min(curBodyPoints + delta, maxBodyPoints);
    }

    private void decreaseBodyPoints(int delta){
        curBodyPoints = Math.max(curBodyPoints - delta, 0);
    }

    protected void equipWeapon(Weapon weapon){
        if(weapon.isTwoHanded()){
            storeLeftWeapon();
            storeRightWeapon();
            leftWeapon = weapon;
        }else{
            if(rightWeapon == null){
                rightWeapon = weapon;
            }
            else{
                storeLeftWeapon();
                leftWeapon = weapon;
            }
        }

        Integer oldAmount = skills.remove(weapon);

        Integer curAmount = oldAmount == null ? 1 : oldAmount + 1;

        skills.put(weapon.getSkill(), curAmount);
        bonusAttackDice += weapon.getAttackBonus();
    }

    private void unequipWeapon(Weapon weapon){
        knapsack.put(weapon);

        Integer curAmount = skills.remove(weapon);

        curAmount --;
        if(curAmount != 0)
            skills.put(weapon.getSkill(), curAmount);

        bonusAttackDice -= weapon.getAttackBonus();
    }

    protected void storeLeftWeapon(){
        if(leftWeapon != null){
            unequipWeapon(leftWeapon);
            leftWeapon = null;
        }
    }

    protected void storeRightWeapon(){
        if(rightWeapon != null){
            unequipWeapon(rightWeapon);
            rightWeapon = null;
        }
    }

    protected void equipArmor(Armor nextArmor){
        if(armor != null)
            unequipArmor();

        armor = nextArmor;
        bonusAttackDice += nextArmor.getDefenceBonus();
    }

    protected void unequipArmor(){
        if(armor != null){
            knapsack.put(armor);
            bonusDefenseDice -= armor.getDefenceBonus();
        }
    }
}
