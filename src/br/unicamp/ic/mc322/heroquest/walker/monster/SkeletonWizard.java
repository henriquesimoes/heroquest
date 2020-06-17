package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class SkeletonWizard extends Monster {
    final int numInitialMagicMissile = 2;

    public SkeletonWizard(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
        this.walkerManager.setWalker(this);
        name = "Skeleton Wizard";
        attackDice = 2;
        defenseDice = 1;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        ableLearnAirSpell = true;

        for (int i = 0; i < numInitialMagicMissile; i++)
            addSkill(new MagicMissile());
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("Ŝ");
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Skeleton Wizard";
    }
}
