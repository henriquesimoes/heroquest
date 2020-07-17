package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

public abstract class Hero extends Walker {
    protected Hero(String name, IOInterface ioInterface) {
        super(new WalkerPlayer(ioInterface), name);
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
