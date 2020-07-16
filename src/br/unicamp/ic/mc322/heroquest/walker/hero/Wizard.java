package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magic.FireBall;
import br.unicamp.ic.mc322.heroquest.skills.magic.MagicMissile;
import br.unicamp.ic.mc322.heroquest.skills.magic.Teleport;
import br.unicamp.ic.mc322.heroquest.walker.Hero;

public class Wizard extends Hero {
    private final int initialNumberOfDaggers = 3;
    private final int initialNumberOfMagicMissiles = 3;

    public Wizard(String name) {
        super(name);

        attackDice = 1;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 4;
        mindPoints = 6;
        ableToLearnAirSpell = ableToLearnEarthSpell = ableToLearnFireSpell = true;

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
