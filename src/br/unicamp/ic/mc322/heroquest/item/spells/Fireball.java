package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Fireball extends MagicSkill {
    private final int ADJACENT_DAMAGE = 3;
    private final int DAMAGE_TO_PRIMARY_TARGET = 6;

    public Fireball() {
        super("Fireball", "Causa 6 de dano ao alvo. Causa 3 danos nos mobs adjacentes");
    }

    /**TODO:
     * verificar se o visible map está funcionando */
    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, Walker targetWalker) {
        if (summoner.attemptMagicalMovement()) {
            targetWalker.defendsMagicSkill(DAMAGE_TO_PRIMARY_TARGET);

            ArrayList<Walker> adjacentTargets = visibleMap.getEnemiesInAdjacentPositions(targetWalker);

            for (Walker target : adjacentTargets) {
                target.defendsMagicSkill(ADJACENT_DAMAGE);
            }
        }
    }
}
