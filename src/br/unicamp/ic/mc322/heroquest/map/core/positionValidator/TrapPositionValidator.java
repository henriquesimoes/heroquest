package br.unicamp.ic.mc322.heroquest.map.core.positionValidator;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.PositionValidator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
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

public class TrapPositionValidator implements PositionValidator, ConcreteMapObjectVisitor {
    private Map map;
    private boolean lastVisitedIsValid;

    public TrapPositionValidator(Map map) {
        this.map = map;
    }

    @Override
    public boolean isValid(Coordinate coordinate) {
        map.accept(this, coordinate);
        return lastVisitedIsValid;
    }

    @Override
    public boolean isExpandable(Coordinate coordinate) {
        return false;
    }

    @Override
    public void visit(Barbarian barbarian) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Dwarf dwarf) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Elf elf) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Wizard wizard) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(WizardSkeleton wizardSkeleton) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(CommonSkeleton commonSkeleton) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Goblin goblin) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Floor floor) {
        lastVisitedIsValid = true;
    }

    @Override
    public void visit(Wall wall) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Door door) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(SecretDoor secretDoor) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Chest chest) {
        lastVisitedIsValid = false;
    }

    @Override
    public void visit(Trap trap) {
        lastVisitedIsValid = false;
    }
}
