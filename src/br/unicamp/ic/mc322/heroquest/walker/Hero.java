package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;

public abstract class Hero extends Walker {
    protected Hero(WalkerManager manager, String name) {
        super(manager, name);
        team = Team.HEROES;
    }

    @Override
    public int getDefenseIntensity(int numberOfDice) {
        int intensity = 0;

        for (int times = 0; times < numberOfDice; times++)
            if (combatDice.roll() == CombatDiceFace.HERO_SHIELD)
                intensity++;

        return intensity;
    }

}
