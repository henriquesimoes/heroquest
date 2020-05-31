package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Monster extends Walker {
    @Override
    public int getIntensityOfPhysicalDefense() {
        int totalDefense = defenseDice + bonusDefenseDice;
        int intensity = 0;

        for (int times = 0; times < totalDefense; times++) {
            if (combatDice.roll() == CombatDiceFace.MONSTER_SHIELD)
                intensity++;
        }

        return intensity;
    }
}
