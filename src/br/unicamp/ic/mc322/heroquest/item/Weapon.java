package br.unicamp.ic.mc322.heroquest.item;

public class Weapon extends DurableItem {
   private int attackBonus;
    private int attackDistance;
    private boolean attackDiagonally;
    private boolean twoHanded;

    public Weapon(String name, String description, int weaponDurability, int goldCoinsValue) {
        super(name, description, weaponDurability, goldCoinsValue);
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
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