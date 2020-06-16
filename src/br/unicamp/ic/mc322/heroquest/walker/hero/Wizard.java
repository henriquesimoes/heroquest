package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.FireBall;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicMissile;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.Teleport;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.Dagger;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Wizard extends Hero {
    public Wizard(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
        this.walkerManager.setWalker(this);
        final int numInitialDaggers = 3;
        final int numInitialMagicMissile = 3;

        attackDice = 1;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints =  4;
        mindPoints = 6;
        ableLearnAirSpell = ableLearnEarthSpell = ableLearnFireSpell = true;

        Weapon curWeapon = new Dagger();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        for (int i = 0; i < numInitialDaggers - 1; i++)
            knapsack.put(new Dagger());

        for (int i = 0; i < numInitialMagicMissile; i++)
            addSkill(new MagicMissile());

        addSkill(new FireBall());
        addSkill(new Teleport());
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("W");
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Wizard: " + getName();
    }
}
