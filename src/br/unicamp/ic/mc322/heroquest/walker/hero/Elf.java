package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.SimpleHeal;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Elf extends Hero {
    public Elf(WalkerManager walkerManager, String name) {
        this.walkerManager = walkerManager;
        this.walkerManager.setWalker(this);

        this.name = name;
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints =  6;
        mindPoints = 4;

        ableLearnWaterSpell = ableLearnEarthSpell = true;

        Weapon curWeapon = new ShortSword();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        addSkill(new SimpleHeal());
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("E");
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Elf: " + getName();
    }
}
