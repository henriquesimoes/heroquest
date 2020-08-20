package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public enum HeroKind {
    BARBARIAN {
        @Override
        public Walker getHero(String name, IOInterface io) {
            return new Barbarian(name, io);
        }

        @Override
        public String toString() {
            return "Barbarian";
        }
    },
    DWARF {
        @Override
        public Walker getHero(String name, IOInterface io) {
            return new Dwarf(name, io);
        }

        @Override
        public String toString() {
            return "Dwarf";
        }
    },
    ELF {
        @Override
        public Walker getHero(String name, IOInterface io) {
            return new Elf(name, io);
        }

        @Override
        public String toString() {
            return "Elf";
        }
    },
    WIZARD {
        @Override
        public Walker getHero(String name, IOInterface io) {
            return new Wizard(name, io);
        }

        @Override
        public String toString() {
            return "Wizard";
        }
    };

    public abstract Walker getHero(String name, IOInterface io);
}
