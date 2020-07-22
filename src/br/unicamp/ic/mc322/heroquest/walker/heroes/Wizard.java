package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Hero;
import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.FireBall;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.MagicMissile;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.Teleport;

import java.util.Arrays;

public class Wizard extends Hero {
    private final int initialNumberOfDaggers = 3;
    private final int initialNumberOfMagicMissiles = 3;

    public Wizard(String name, IOInterface ioInterface) {
        super(name, ioInterface);

        attackDice = 1;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 20;
        mindPoints = 6;
        itemsAbleToUse.add(ItemClass.MAGICIAN);
        spellsAbleToLearn.addAll(Arrays.asList(SpellElement.AIR, SpellElement.EARTH, SpellElement.FIRE));

        Weapon currentWeapon = new Dagger();
        knapsack.put(currentWeapon);
        equipWeapon(currentWeapon);

        for (int i = 0; i < initialNumberOfDaggers - 1; i++)
            knapsack.put(new Dagger());

        for (int i = 0; i < initialNumberOfMagicMissiles; i++)
            addSkill(new MagicMissile());

        addSkill(new FireBall());
        addSkill(new Teleport());
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Wizard: " + getName();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
