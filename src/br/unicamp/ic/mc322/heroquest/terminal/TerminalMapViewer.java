package br.unicamp.ic.mc322.heroquest.terminal;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.SecretDoor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.view.MapViewer;
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.hero.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Elf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.WizardSkeleton;

public class TerminalMapViewer implements MapViewer {
    private Map map;
    private char output[][];

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
            for (int j = 0; j < output[i].length; j++)
                output[i][j] = '#';
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
        setSymbol(secretDoor, '#');
    }

    @Override
    public void visit(Chest chest) {
        setSymbol(chest, 'c');
    }
}
