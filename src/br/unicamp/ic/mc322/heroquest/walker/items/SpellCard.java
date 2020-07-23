package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.air.MagicMissileCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.earth.SimpleHealCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.earth.TeleportCard;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.fire.FireBallCard;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

public abstract class SpellCard extends HeapItem {
    protected Skill spell;
    private SpellElement spellElement;

    protected SpellCard(String itemName, String itemDescription, int goldCoinsValue, SpellElement spellElement) {
        super(itemName, itemDescription + "\nSpell Element: " + spellElement.toString(), goldCoinsValue);
        this.spellElement = spellElement;
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

    @Override
    public void useItem(Walker proprietary) {
        if (proprietary.isAbleToLearn(spellElement)) {
            proprietary.addSkill(spell);
            proprietary.destroyItem(this);
        } else
            proprietary.getManager().showMessage("You are not able to learn this spell");
    }
}
