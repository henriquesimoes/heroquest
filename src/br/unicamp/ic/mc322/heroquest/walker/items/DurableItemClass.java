package br.unicamp.ic.mc322.heroquest.walker.items;

public enum DurableItemClass {
    WARRIOR {
        @Override
        public String toString() {
            return "Warrior";
        }
    },
    MAGICIAN {
        @Override
        public String toString() {
            return "Magician";
        }
    },
    NEUTRAL {
        @Override
        public String toString() {
            return "Neutral";
        }
    }
}
