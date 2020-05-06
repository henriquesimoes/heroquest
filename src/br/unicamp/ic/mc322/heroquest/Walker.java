package br.unicamp.ic.mc322.heroquest;

import java.util.LinkedHashMap;

public abstract class Walker {
    protected Weapon leftWeapon, rightWeapon;
    protected int bodyPoints, mindPoints, attackDice, moveDice, defenseDice, bonusAttackDice;
    protected LinkedHashMap < Skill, Integer > skills;
    protected Knapsack knapsack;

    Walker(){
        knapsack = new Knapsack();
        skills = new LinkedHashMap < Skill, Integer >();
        moveDice = 2;
    }

    protected void equipWeapon(Weapon weapon){
        if(weapon.isTwoHand()){
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

        Integer oldAmount = skills.get(weapon);
        skills.remove(weapon);

        Integer curAmount = oldAmount == null ? 1 : oldAmount + 1;

        skills.put(weapon.getSkill(), curAmount);
        bonusAttackDice += weapon.getBonusAttack();
    }

    private void unequipWeapon(Weapon weapon){
        knapsack.put(weapon);

        Integer curAmount = skills.get(weapon);
        skills.remove(weapon.getSkill());

        curAmount --;
        if(curAmount != 0)
            skills.put(weapon.getSkill(), curAmount);

        bonusAttackDice -= weapon.getBonusAttack();
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
}
