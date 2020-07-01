package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class SkeletonWizard extends Monster {
    final int numInitialMagicMissile = 2;

    public SkeletonWizard(WalkerManager walkerManager) {
        super(walkerManager, "Skeleton Wizard");

        attackDice = 2;
        defenseDice = 1;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        ableLearnAirSpell = true;

        for (int i = 0; i < numInitialMagicMissile; i++)
            addSkill(new MagicMissile());
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Skeleton Wizard";
    }

    @Override
    public void accept(MapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
