package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.ShortSword;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class Dwarf extends Hero {
    Dwarf() {
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 7;
        mindPoints = 3;

        Weapon curWeapon = new ShortSword();
        knapsack.put(curWeapon);

        equipWeapon(curWeapon);
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("W");
    }

    @Override
    public String getRepresentationOnMenu() {
        return null;
    }
}
