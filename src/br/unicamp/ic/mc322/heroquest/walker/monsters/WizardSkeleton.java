package br.unicamp.ic.mc322.heroquest.walker.monsters;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Monster;
import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement.RandomMovement;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.MagicMissile;

public class WizardSkeleton extends Monster {
    final int initialNumberOfMagicMissiles = 2;

    public WizardSkeleton() {
        super(new WalkerAI(new RandomMovement(), new Bloodthirsty()), "Wizard Skeleton");

        attackDice = 2;
        defenseDice = 1;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 3;
        itemsAbleToUse.add(ItemClass.MAGICIAN);
        spellsAbleToLearn.add(SpellElement.AIR);

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