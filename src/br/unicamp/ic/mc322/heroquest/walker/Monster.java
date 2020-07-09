package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;

public abstract class Monster extends Walker {
    protected Monster(WalkerManager manager, String name) {
        super(manager, name);
        team = Team.MORCAR;
    }

    @Override
    public int getDefenseIntensity(int numberOfDices) {
        int intensity = 0;

        for (int times = 0; times < numberOfDices; times++)
            if (combatDice.roll() == CombatDiceFace.MONSTER_SHIELD)
                intensity++;

        return intensity;
    }
}
