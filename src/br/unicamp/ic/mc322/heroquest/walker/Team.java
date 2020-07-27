package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.util.dice.CombatDiceFace;

public enum Team {
    HEROES {
        @Override
        public CombatDiceFace getFavorableDiceFace() {
            return CombatDiceFace.HERO_SHIELD;
        }
    },
    MORCAR {
        @Override
        public CombatDiceFace getFavorableDiceFace() {
            return CombatDiceFace.MONSTER_SHIELD;
        }
    };

    public abstract CombatDiceFace getFavorableDiceFace();
}