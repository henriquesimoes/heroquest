package br.unicamp.ic.mc322.heroquest;

public class Skill {
    SkillType type;
    Action action;
    int intensity;

    SkillType getType(){
        return type;
    }

    Action getAction(){
        return action;
    }
}
