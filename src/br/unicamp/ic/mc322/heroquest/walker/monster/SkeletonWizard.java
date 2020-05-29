package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.spells.MagicMissile;

public class SkeletonWizard extends Skeleton {

    SkeletonWizard(){
        super();
        final int numInitialMagicMissile = 2;
        attackDice = 2;
        defenseDice = 1;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        for (int i = 0; i < numInitialMagicMissile; i++)
            addMagicSkill(new MagicMissile());
        // TODO: implements use of the fists
    }
}
