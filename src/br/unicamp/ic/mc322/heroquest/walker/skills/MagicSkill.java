package br.unicamp.ic.mc322.heroquest.walker.skills;

import java.util.Objects;

public abstract class MagicSkill extends Skill {
    public MagicSkill(String spellName, String spellDescription, DisplayTargetsMode displayTargetsMode) {
        super(spellName, spellDescription, displayTargetsMode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill that = (Skill) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int compareTo(Object o) {
        Skill that = (Skill) o;
        return getName().compareTo(that.getName());
    }
}
