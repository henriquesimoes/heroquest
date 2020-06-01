package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.item.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleporte", "O herói ou monstro se teletransporta para uma posição visível");
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, Walker targetWalker) {
       /**TODO:
        * esperar ter mais coisas do mapa para fazer*/
    }
}
