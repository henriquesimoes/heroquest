package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.walker.items.cards.air.MagicMissileCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.earth.SimpleHealCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.earth.TeleportCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.fire.FireBallCard;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

public abstract class SpellCard extends HeapItem {
    protected Skill spell;

    protected SpellCard(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
    }

    public static SpellCard getRandomSpellCard() {
        SpellCard[] possibleSpellCards = {
                new MagicMissileCard(),
                new SimpleHealCard(),
                new TeleportCard(),
                new FireBallCard()
        };

        int choice = Randomizer.nextInt(possibleSpellCards.length);

        return possibleSpellCards[choice];
    }
}
