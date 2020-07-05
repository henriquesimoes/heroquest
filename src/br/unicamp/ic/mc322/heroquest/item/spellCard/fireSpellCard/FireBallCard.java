package br.unicamp.ic.mc322.heroquest.item.spellCard.fireSpellCard;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.FireBall;

public class FireBallCard extends FireSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell FireBall.";

    public FireBallCard() {
        super("FireBallCard", DESCRIPTION, 100);

        spell = new FireBall();
    }

}
