package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.LongSword;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.WalkerPlayer;

public class Barbarian extends Walker {
    public Barbarian(String name, IOInterface ioInterface) {
        super(new WalkerPlayer(ioInterface), name);

        team = Team.HEROES;
        attackDice = 3;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 40;
        mindPoints = 2;
        durableItemsAbleToUse.add(DurableItemClass.WARRIOR);

        Weapon currentWeapon = new LongSword();
        knapsack.put(currentWeapon);

        equipWeapon(currentWeapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Barbarian " + getName();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
