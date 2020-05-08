package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells;

public class LongRangeWeapon extends Weapon {
    public int throwDistance;

    public LongRangeWeapon(String name, String description, int weaponDurability, int goldCoinsValue) {
        super(name, description, weaponDurability, goldCoinsValue);
    }

    public void setThrowDistance(int distance) {
        this.throwDistance = distance;
    }

    public int getThrowDistance() {
        return throwDistance;
    }

    public void throwWeapon() {
        /** TODO
         * DECIDIR COMO VÃO FUNCIONAR OS LANÇAMENTOS*/
    }
}
