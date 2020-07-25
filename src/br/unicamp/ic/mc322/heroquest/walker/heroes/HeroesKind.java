package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public enum HeroesKind {
    BARBARIAN {
        @Override
        Walker getHeroInstance(String name, IOInterface io) {
            return new Barbarian(name, io);
        }
    },
    DWARF {
        @Override
        Walker getHeroInstance(String name, IOInterface io) {
            return new Dwarf(name, io);
        }
    },
    ELF {
        @Override
        Walker getHeroInstance(String name, IOInterface io) {
            return new Elf(name, io);
        }
    },
    WIZARD {
        @Override
        Walker getHeroInstance(String name, IOInterface io) {
            return new Wizard(name, io);
        }
    };

    public static String[] getHeroesList() {
        String[] heroesList = new String[HeroesKind.values().length];

        int i = 0;
        for (HeroesKind heroType : HeroesKind.values()) {
            heroesList[i] = heroType.toString().toLowerCase();

            i++;
        }

        return heroesList;
    }

    abstract Walker getHeroInstance(String name, IOInterface io);

    public Walker getHero(String name, IOInterface io) {
        return this.getHeroInstance(name, io);
    }
}
