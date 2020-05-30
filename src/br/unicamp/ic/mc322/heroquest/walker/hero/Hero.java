package br.unicamp.ic.mc322.heroquest.walker.hero;


import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Hero extends Walker {
    public Hero() {
        super();
    }

    @Override
    public int getIntensityOfPhysicalDefense() {
        int totalDefense = defenseDice + bonusDefenseDice;
        int intensity = 0;

        for (int times = 0; times < totalDefense; times++){
            if(combatDice.roll() == CombatDiceFace.HERO_SHIELD)
                intensity++;
        }

        return intensity;
    }

}
