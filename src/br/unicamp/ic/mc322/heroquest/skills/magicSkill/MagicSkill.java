package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.skills.Skill;

public abstract class MagicSkill extends Skill {
    private String spellDescription;

    public MagicSkill(String SpellName, String spellDescription) {
        super(SpellName);
        this.spellDescription = spellDescription;
    }
}