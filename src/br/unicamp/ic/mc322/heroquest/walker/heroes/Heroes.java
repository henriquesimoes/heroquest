package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public enum Heroes {
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

    abstract Walker getHeroInstance(String name, IOInterface io);

    public Walker getHero(String name, IOInterface io) {
        return this.getHeroInstance(name, io);
    }

    public static String[] getHeroesList() {
        String[] heroesList = new String[Heroes.values().length];

        int i = 0;
        for (Heroes heroType : Heroes.values()) {
            heroesList[i] = heroType.toString().toLowerCase();

            i++;
        }

        return heroesList;
    }
}
