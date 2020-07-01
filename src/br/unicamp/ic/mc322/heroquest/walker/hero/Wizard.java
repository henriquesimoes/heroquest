package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.FireBall;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.Teleport;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Wizard extends Hero {
    private final int initialNumberOfDaggers = 3;
    private final int initialNumberOfMagicMissiles = 3;

    public Wizard(WalkerManager walkerManager, String name) {
        super(walkerManager, name);

        attackDice = 1;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints =  4;
        mindPoints = 6;
        ableLearnAirSpell = ableLearnEarthSpell = ableLearnFireSpell = true;

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
    public void accept(MapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
