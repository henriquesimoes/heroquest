package br.unicamp.ic.mc322.heroquest.item.skills;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;

public class Skill {
    private String skillName;
    private int skillIntensity;
    private CollectableItem skilledItem;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill(String skillName, CollectableItem skilledItem) {
        this.skillName = skillName;
        this.skilledItem = skilledItem;
    }


    public CollectableItem getSkilledItem() {
        return skilledItem;
    }

    public void useSkill() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillIntensity(int intensity) {
        this.skillIntensity = intensity;
    }

    public int getSkillIntensity() {
        return skillIntensity;
    }
}
