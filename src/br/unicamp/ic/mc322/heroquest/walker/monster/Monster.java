package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public abstract class Monster extends Walker {
    Monster(WalkerManager manager, String name) {
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
