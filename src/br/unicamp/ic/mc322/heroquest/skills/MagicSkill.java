package br.unicamp.ic.mc322.heroquest.skills;

public abstract class MagicSkill extends Skill {
    private String spellDescription;

    public MagicSkill(String SpellName, String spellDescription) {
        super(SpellName);
        this.spellDescription = spellDescription;
    }
}