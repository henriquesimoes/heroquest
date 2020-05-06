package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells;

public class Weapon extends Item {
    private int attackPower;
    private int attackBonus;
    private int attackDistance;
    private boolean diagonallyAttack;
    private boolean twoHanded;

    public static class Builder {
        private String weaponName;
        private String weaponDescription;
        private int attackPower;
        private int attackBonus;
        private int attackDistance;
        private boolean diagonallyAttack;
        private boolean twoHanded;
        private int weaponDurability;

        public Builder() {}

        public Builder setWeaponName(String weaponName) {
            this.weaponName = weaponName;
            return this;
        }

        public Builder setWeaponDescription(String weaponDescription) {
            this.weaponDescription =  weaponDescription;
            return this;
        }

        public Builder setAttackPower(int attackPower) {
            this.attackPower = attackPower;
            return this;
        }

        public Builder setAttackDistance(int attackDistance) {
            this.attackDistance = attackDistance;
            return this;
        }

        public Builder setDiagonallyAttack(boolean diagonallyAttack) {
            this.diagonallyAttack = diagonallyAttack;
            return this;
        }

        public Builder setAsTwoHandedWeapon(boolean twoHanded) {
            this.twoHanded = twoHanded;
            return this;
        }

        public Builder setWeaponDurability(int weaponDurability) {
            this.weaponDurability = weaponDurability;
            return this;
        }

        public Weapon build() {
            return new Weapon(this);
        }
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

    public boolean canDiagonallyAttack() {
        return diagonallyAttack;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    private Weapon(Builder builder) {
        super(builder.weaponName, builder.weaponDescription, builder.weaponDurability);
        this.attackPower = builder.attackPower;
        this.attackBonus = builder.attackBonus;
        this.attackDistance = builder.attackDistance;
        this.diagonallyAttack = builder.diagonallyAttack;
        this.twoHanded = builder.twoHanded;
    }
}
