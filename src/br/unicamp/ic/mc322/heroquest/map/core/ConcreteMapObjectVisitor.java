package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.hero.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Elf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.WizardSkeleton;

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
}
