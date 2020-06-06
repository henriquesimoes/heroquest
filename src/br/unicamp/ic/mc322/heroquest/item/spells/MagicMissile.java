package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MagicMissile extends MagicSkill {
    private final int TOTAL_DAMAGE = 6;

    public MagicMissile() {
        super("Magic Missile", "Lança três flechas mágicas, cada uma causando 2 de dano.");
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, Walker targetWalker) {
        if (summoner.attemptMagicalMovement()) {
            targetWalker.defendsMagicSkill(TOTAL_DAMAGE);
        }
    }

}
