package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.skills.Skill;

import java.util.Objects;

public abstract class MagicSkill extends Skill {
    private String spellDescription;

    public MagicSkill(String SpellName, String spellDescription) {
        super(SpellName);
        this.spellDescription = spellDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MagicSkill that = (MagicSkill) o;
        return Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }
}