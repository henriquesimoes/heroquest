package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.SpellCard;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.skill.MagicSkill;
import br.unicamp.ic.mc322.heroquest.skill.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.skill.Skill;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDice;
import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.util.dice.Dice;
import br.unicamp.ic.mc322.heroquest.util.dice.RedDice;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;

public abstract class Walker {
    protected Weapon leftWeapon, rightWeapon;
    protected Armor armor;
    protected int maxBodyPoints, curBodyPoints, mindPoints, attackDice, moveDice, defenseDice, bonusDefenseDice;
    protected ArrayList< Pair < PhysicalSkill, Integer > > physicalSkills;
    protected ArrayList< Pair < MagicSkill, Integer > > magicSkills;
    protected CombatDice combatDice;
    protected RedDice redDice;
    protected Dice magicDice;
    protected Knapsack knapsack;

    Walker(){
        redDice = new RedDice();
        combatDice = new CombatDice();
        magicDice = new Dice(6);
        knapsack = new Knapsack();
        physicalSkills = new ArrayList<>();
        magicSkills = new ArrayList<>();
        moveDice = 2;
    }

    public ArrayList<String> getListPhysicalSkills(){
        ArrayList<String> listName = new ArrayList<>();
        for(Pair<PhysicalSkill, Integer> pair : physicalSkills){
            PhysicalSkill skill = pair.getKey();
            listName.add(skill.getName());
        }
        return listName;
    }

    public ArrayList<String> getListMagicSkills(){
        ArrayList<String> listName = new ArrayList<>();
        for(Pair<MagicSkill, Integer> pair : magicSkills){
            MagicSkill skill = pair.getKey();
            listName.add(skill.getName());
        }
        return listName;
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

    public int getIntensitySkill(MagicSkill skill){
        return skill.getIntensity(magicDice.rollIndex());
    }

    public int getIntensitySkill(PhysicalSkill skill){
        int intensity = 0;
        Weapon weapon = (Weapon)skill.getItem();
        int totalAttack = attackDice + weapon.getAttackBonus();

        for(int times = 0; times < totalAttack; times++)
            if(combatDice.roll() == CombatDiceFace.SKULL)
                intensity++;
        return intensity;
    }

    public int usePhysicalSkill(int index, boolean useInYourSelf){
        PhysicalSkill skill = physicalSkills.get(index).getKey();
        int intensity = getIntensitySkill(skill);
        if(useInYourSelf)
            executeAction(skill.getAction(), intensity);
        useSkill(skill);
        return intensity;
    }

    public int useMagicSkill(int index, boolean useInYourSelf){
        MagicSkill skill = magicSkills.get(index).getKey();
        boolean sucessUse = tryUseMagicSkill();
        int intensity = sucessUse ? getIntensitySkill(skill) : 0;
        if(sucessUse && useInYourSelf)
            executeAction(skill.getAction(), intensity);
        useSkill(skill);
        return intensity;
    }

    private boolean tryUseMagicSkill(){
        return magicDice.rollIndex() <= mindPoints;
    }

    public void useSkill(Skill skill){
        CollectableItem item = skill.getItem();
        if(item.isDestroy())
            destroyItem(item);
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
        if (item instanceof SpellCard)
            removeSkillSpellCard((SpellCard)item);

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

        ArrayList<PhysicalSkill> skills = weapon.getSkills();

        for(PhysicalSkill skill : skills){
            // TODO: test if this really work
            int index = physicalSkills.indexOf(new Pair<PhysicalSkill, Integer>(skill, 0));

            if(index == -1){
                physicalSkills.add(new Pair<>(skill, 1));
            }else{
                Pair<PhysicalSkill, Integer> pair = physicalSkills.get(index);
                pair.setValue(pair.getValue() + 1);
            }
        }
    }

    private void unequipWeapon(Weapon weapon){
        knapsack.put(weapon);

        ArrayList<PhysicalSkill> skills = weapon.getSkills();

        for(PhysicalSkill skill : skills){
            // TODO: test if this really work
            int index = physicalSkills.indexOf(new Pair<PhysicalSkill, Integer>(skill, 0));

            if(index == -1){
                System.out.println("Fatal Error");
                System.exit(1);
            }else{
                Pair<PhysicalSkill, Integer> pair = physicalSkills.get(index);
                if(pair.getValue() == 1){
                    physicalSkills.remove(index);
                }else{
                    pair.setValue(pair.getValue() - 1);
                }
            }
        }
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
        bonusDefenseDice += nextArmor.getDefenceBonus();
    }

    protected void unequipArmor(){
        if(armor != null){
            knapsack.put(armor);
            bonusDefenseDice -= armor.getDefenceBonus();
        }
    }

    protected void removeSkillSpellCard(SpellCard card){
        MagicSkill skill = card.getSkill();
        // TODO: test if this really work
        int index = magicSkills.indexOf(new Pair<MagicSkill, Integer>(skill, 0));

        if(index == -1){
            System.out.println("Fatal Error");
            System.exit(1);
        }else{
            Pair<MagicSkill, Integer> pair = magicSkills.get(index);
            if(pair.getValue() == 1)
                magicSkills.remove(index);
            else
                pair.setValue(pair.getValue() - 1);
        }
    }

    protected void addSkillSpellCard(SpellCard card){
        MagicSkill skill = card.getSkill();
        // TODO: test if this really work
        int index = magicSkills.indexOf(new Pair<MagicSkill, Integer>(skill, 0));

        if(index == -1){
            magicSkills.add(new Pair<>(skill, 1));
        }else{
            Pair<MagicSkill, Integer> pair = magicSkills.get(index);
            pair.setValue(pair.getValue() + 1);
        }
    }

    protected void collectItem(CollectableItem item){
        knapsack.put(item);
        if(item instanceof SpellCard){
            addSkillSpellCard((SpellCard)item);
        }
    }
}
