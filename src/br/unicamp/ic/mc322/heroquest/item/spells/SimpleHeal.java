package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class SimpleHeal extends MagicSkill {
    public SimpleHeal() {
        super("Simple Heal spell", "Cura um valor de 1 a 6 pontos vida. " +
                "O valor é o número obtido após o lançamento de um dado de seis faces");
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, Walker targetWalker) {
        if (summoner.attemptMagicalMovement()) {
            summoner.restoreBodyPoints(summoner.rollRedDice());
        }
    }
}