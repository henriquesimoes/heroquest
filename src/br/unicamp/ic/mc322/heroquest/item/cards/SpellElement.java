package br.unicamp.ic.mc322.heroquest.item.cards;

public enum SpellElement {
    AIR {
        @Override
        public String toString() {
            return "Air";
        }
    },
    EARTH {
        @Override
        public String toString() {
            return "Earth";
        }
    },
    FIRE {
        @Override
        public String toString() {
            return "Fire";
        }
    }
}
