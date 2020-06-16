package br.unicamp.ic.mc322.heroquest.walker.hero;


import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Hero extends Walker {

    Hero(){
        team = Team.HEROES;
    }

    @Override
    public int getIntensityDefense(int numberOfDices) {
        int intensity = 0;

        for (int times = 0; times < numberOfDices; times++)
            if (combatDice.roll() == CombatDiceFace.HERO_SHIELD)
                intensity++;

        return intensity;
    }

}
