package br.unicamp.ic.mc322.heroquest.item.spells;

public class MagicMissile extends MagicSkill {
    private int totalDamage = 6;

    public MagicMissile() {
        super("Magic Missile", "Lança três flechas mágicas, cada uma causando 2 de dano.");
    }

    @Override
    public void useSkill() {
    }

    public int getTotalDamage() {
        return totalDamage;
    }
}
