package br.unicamp.ic.mc322.heroquest.item.spells;

public class Fireball extends MagicSkill {
    private int adjacentDamage = 3;
    private int damageToTarget = 6;

    public Fireball() {
        super("Fireball", "Causa 6 de dano ao alvo. Causa 3 danos nos mobs adjacentes");
    }

    @Override
    public void useSkill() {
        super.useSkill();
    }
}
