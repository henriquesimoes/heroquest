package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class CommonSkeleton extends Skeleton {
    CommonSkeleton(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 1;
        // TODO: See how the skeleton will generate its weapon
        Weapon weapon = null;

        knapsack.put(weapon);
        equipWeapon(weapon);
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("S");
    }
}
