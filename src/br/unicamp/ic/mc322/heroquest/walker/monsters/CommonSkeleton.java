package br.unicamp.ic.mc322.heroquest.walker.monsters;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement.RandomMovement;

public class CommonSkeleton extends Walker {
    public CommonSkeleton() {
        super(new WalkerAI(new RandomMovement(), new Bloodthirsty()), "Common Skeleton");

        team = Team.MORCAR;
        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 1;

        Weapon weapon = Weapon.getRandomWeapon();
        knapsack.put(weapon);
        equipWeapon(weapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Common Skeleton";
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
