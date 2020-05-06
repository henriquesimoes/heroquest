package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells;

public class Weapon extends Item {
    private int attackPower;
    private int attackBonus;
    private int attackDistance;
    private boolean canDiagonallyAttack;

    public Weapon(String weaponName, String weaponDescription, int weaponDurability) {
        super(weaponName, weaponDescription, weaponDurability);
    }
}
