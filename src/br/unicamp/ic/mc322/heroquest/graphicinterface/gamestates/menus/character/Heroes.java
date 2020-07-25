package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.character;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Elf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;

public enum Heroes {
    BARBARIAN {
        @Override
        Walker getHero(String name, IOInterface io) {
            //**TODO:
            // @param TerminalIO apenas para impedir erro ao compilar, será mudado*/
            return new Barbarian(name, io);
        }
    },
    DWARF {
        @Override
        Walker getHero(String name, IOInterface io) {
            //**TODO:
            // @param TerminalIO apenas para impedir erro ao compilar, será mudado*/
            return new Dwarf(name, io);
        }
    },
    ELF {
        @Override
        Walker getHero(String name, IOInterface io) {
            //**TODO:
            // @param TerminalIO apenas para impedir erro ao compilar, será mudado*/
            return new Elf(name, io);
        }
    },
    WIZARD {
        @Override
        Walker getHero(String name, IOInterface io) {
            //**TODO:
            // @param TerminalIO apenas para impedir erro ao compilar, será mudado*/
            return new Wizard(name, io);
        }
    };

    abstract Walker getHero(String name, IOInterface io);
}
