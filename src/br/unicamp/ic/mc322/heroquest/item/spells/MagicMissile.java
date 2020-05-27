package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.item.spells.MagicSkill;

public class MagicMissile extends MagicSkill {
    private int totalDamage = 6;

    public MagicMissile() {
        super("Magic Missile", "Lança três flechas mágicas, cada uma causando 2 de dano.");
    }

    @Override
    public void useSkill() {
        super.useSkill();
    }

    public int getTotalDamage() {
        return totalDamage;
    }
}
