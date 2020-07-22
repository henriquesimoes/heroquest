package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.view.MapViewer;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Elf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

import java.util.Arrays;

public class TerminalMapViewer implements MapViewer {
    private final Map map;
    private final char[][] output;

    public TerminalMapViewer(Map map) {
        this.map = map;

        output = new char[map.getHeight()][map.getWidth()];
    }

    @Override
    public void display(Coordinate reference) {
        clear();
        RegionSelector regionSelector = map.getRegionSelector();
        Region region = regionSelector.getVisibleRegion(reference, false);
        map.accept(this, region);
        print();
    }

    private void clear() {
        for (int i = 0; i < output.length; i++)
            Arrays.fill(output[i], '?');
    }

    private void print() {
        StringBuilder builder = new StringBuilder();

        builder.append("  ");

        for (int dx = 0; dx < map.getWidth(); dx++)
            builder.append(String.format("%3d", dx));

        builder.append("\n");

        for (int dy = 0; dy < map.getHeight(); dy++) {
            // TODO: encapsulate rows creation
            builder.append(String.format("%2d ", dy));

            for (int dx = 0; dx < map.getWidth(); dx++)
                builder.append(" " + output[dy][dx] + " ");

            // TODO: encapsulate row wrap-up
            builder.append("\n");
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }

    private void setSymbol(MapObject object, char representation) {
        output[object.getY()][object.getX()] = representation;
    }

    @Override
    public void visit(Barbarian barbarian) {
        setSymbol(barbarian, 'B');
    }

    @Override
    public void visit(Dwarf dwarf) {
        setSymbol(dwarf, 'F');
    }

    @Override
    public void visit(Elf elf) {
        setSymbol(elf, 'E');
    }

    @Override
    public void visit(Wizard wizard) {
        setSymbol(wizard, 'W');
    }

    @Override
    public void visit(WizardSkeleton wizardSkeleton) {
        setSymbol(wizardSkeleton, 'Ŝ');
    }

    @Override
    public void visit(CommonSkeleton commonSkeleton) {
        setSymbol(commonSkeleton, 'S');
    }

    @Override
    public void visit(Goblin goblin) {
        setSymbol(goblin, 'G');
    }

    @Override
    public void visit(Floor floor) {
        setSymbol(floor, ' ');
    }

    @Override
    public void visit(Wall wall) {
        setSymbol(wall, '#');
    }

    @Override
    public void visit(Door door) {
        setSymbol(door, door.isOpen() ? ' ' : 'D');
    }

    @Override
    public void visit(SecretDoor secretDoor) {
        if (secretDoor.isDiscovered())
            visit((Door) secretDoor);
        else
            setSymbol(secretDoor, '#');
    }

    @Override
    public void visit(Chest chest) {
        setSymbol(chest, chest.isOpened() ? 'c' : 'C');
    }

    @Override
    public void visit(Trap trap) {
        setSymbol(trap, trap.isDiscovered() ? (trap.isArmed() ? 'T' : 't') : ' ');
    }

}
