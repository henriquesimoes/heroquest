package br.unicamp.ic.mc322.heroquest.skills;

import java.util.Objects;

public abstract class MagicSkill extends Skill {
    private final String spellDescription;

    public MagicSkill(String spellName, String spellDescription) {
        super(spellName);
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