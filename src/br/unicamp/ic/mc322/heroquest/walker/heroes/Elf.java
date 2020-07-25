package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.WalkerPlayer;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.SimpleHeal;

public class Elf extends Walker {
    public Elf(String name, IOInterface ioInterface) {
        super(new WalkerPlayer(ioInterface), name);

        team = Team.HEROES;
        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 20;
        mindPoints = 4;
        durableItemsAbleToUse.add(DurableItemClass.MAGICIAN);
        spellsAbleToLearn.add(SpellElement.EARTH);

        Weapon currentWeapon = new ShortSword();
        knapsack.put(currentWeapon);
        equipWeapon(currentWeapon);

        addSkill(new SimpleHeal());
    }

    @Override
    public String getRepresentationOnMenu() {
        return "ElfChar: " + getName();
    }


    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
