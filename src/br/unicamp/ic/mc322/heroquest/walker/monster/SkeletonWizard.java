package br.unicamp.ic.mc322.heroquest.walker.monster;

public class SkeletonWizard extends Skeleton {

    SkeletonWizard(){
        super();
        final int numInitialMagicMissile = 2;
        attackDice = 2;
        defenseDice = 1;
        maxBodyPoints = curBodyPoints = 2;
        mindPoints = 3;
        for (int i = 0; i < numInitialMagicMissile; i++)
            knapsack.put(new MagicMissileCard());
        // TODO: implements use of the fists
    }
}
