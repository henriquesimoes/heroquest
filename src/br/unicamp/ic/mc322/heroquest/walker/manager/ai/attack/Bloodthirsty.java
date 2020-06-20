package br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

public class Bloodthirsty extends AttackBehavior{
    private static Bloodthirsty instance;

    public Bloodthirsty(){}

    public static Bloodthirsty getInstance(){
        if (instance == null)
            instance = new Bloodthirsty();
        return instance;
    }

    @Override
    public int chooseTarget(ArrayList<MapObject> targets) {
        return Randomizer.randInt(1, targets.size());
    }

    @Override
    public int chooseSkill(ArrayList<Skill> skills) {
        return Randomizer.randInt(1, skills.size());
    }
}
