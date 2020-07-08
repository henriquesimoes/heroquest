package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class WizardSkeleton extends Monster {
    final int initialNumberOfMagicMissiles = 2;

    public WizardSkeleton(WalkerManager walkerManager) {
        super(walkerManager, "Wizard Skeleton");

        attackDice = 2;
        defenseDice = 1;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        ableToLearnAirSpell = true;

        for (int i = 0; i < initialNumberOfMagicMissiles; i++)
            addSkill(new MagicMissile());
    }

    @Override
    public String getRepresentationOnMenu() {
        return name;
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
