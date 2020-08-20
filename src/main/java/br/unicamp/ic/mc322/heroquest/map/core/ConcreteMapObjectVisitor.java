package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Elf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

/**
 * Visitor interface for concrete map objects.
 * <p>
 * This should be used when a specific map object needs to be
 * visited. If a standard behavior is expected for all groups
 * of objects, an `AbstractMapObjectVisitor` realization should
 * be chosen.
 */
public interface ConcreteMapObjectVisitor {
    void visit(Barbarian barbarian);

    void visit(Dwarf dwarf);

    void visit(Elf elf);

    void visit(Wizard wizard);

    void visit(WizardSkeleton wizardSkeleton);

    void visit(CommonSkeleton commonSkeleton);

    void visit(Goblin goblin);

    void visit(Floor floor);

    void visit(Wall wall);

    void visit(Door door);

    void visit(SecretDoor secretDoor);

    void visit(Chest chest);

    void visit(Trap trap);
}
