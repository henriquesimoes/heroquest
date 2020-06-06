package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class SkeletonWizard extends Skeleton {
    SkeletonWizard() {
        final int numInitialMagicMissile = 2;
        attackDice = 2;
        defenseDice = 1;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        for (int i = 0; i < numInitialMagicMissile; i++)
            addSkill(new MagicMissile());

        // TODO: implement use of the fists
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("Ŝ");
    }

    @Override
    public String getRepresentationOnMenu() {
        return null;
    }
}
