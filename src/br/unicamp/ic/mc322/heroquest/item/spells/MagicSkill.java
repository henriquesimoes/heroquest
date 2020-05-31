package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.item.skills.Skill;

public abstract class MagicSkill extends Skill {
    private String spellDescription;

    public MagicSkill(String SpellName, String spellDescription) {
        super(SpellName);
        this.spellDescription = spellDescription;
    }

    public abstract void useSkill();
}