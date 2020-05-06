package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells;

public class Weapon extends Item {
    private int attackPower;
    private int attackBonus;
    private int attackDistance;
    private boolean attackDiagonally;
    private boolean twoHanded;

    public Weapon(String name, String description, int weaponDurability) {
        super(name, description, weaponDurability);
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setAttackDistance(int attackDistance) {
        this.attackDistance = attackDistance;
    }

    public void setAttackDiagonally(boolean attackDiagonally) {
        this.attackDiagonally = attackDiagonally;
    }

    public void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public boolean canAttackDiagonally() {
        return attackDiagonally;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }
}
