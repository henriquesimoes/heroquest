package br.unicamp.ic.mc322.heroquest.walker.skills;

public abstract class MagicSkill extends Skill {
    public MagicSkill(String spellName, String spellDescription, DisplayTargetsMode displayTargetsMode) {
        super(spellName, spellDescription, displayTargetsMode);
    }
}