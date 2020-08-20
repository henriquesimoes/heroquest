package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.WalkerPlayer;

public class Dwarf extends Walker {
    public Dwarf(String name, IOInterface ioInterface) {
        super(new WalkerPlayer(ioInterface), name);

        team = Team.HEROES;
        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 30;
        mindPoints = 3;
        durableItemsAbleToUse.add(DurableItemClass.WARRIOR);

        Weapon currentWeapon = new ShortSword();
        knapsack.put(currentWeapon);

        equipWeapon(currentWeapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Dwarf " + getName();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
