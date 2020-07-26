package br.unicamp.ic.mc322.heroquest.engine.terminal;

import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.*;
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

import java.util.Arrays;

public class TerminalMapViewer implements MapViewer {
    private final Map map;
    private final char[][] visibleMap;
    private final int radiusVisibility;

    public TerminalMapViewer(Map map) {
        this.map = map;
        this.radiusVisibility = VisibleRegion.getMaximumVisibilityRadius();

        visibleMap = new char[map.getHeight()][map.getWidth()];
    }

    @Override
    public void display(Coordinate reference) {
        clear();

        RegionSelector regionSelector = map.getRegionSelector();
        Region region = regionSelector.getVisibleRegion(reference);

        // update the map objects only on the visible region
        map.accept(this, region);

        print(reference);
    }

    private void clear() {
        for (int i = 0; i < visibleMap.length; i++)
            Arrays.fill(visibleMap[i], '?');
    }

    private void print(Coordinate reference) {
        StringBuilder builder = new StringBuilder();
        char[][] output = Centralizer.getCentralizeMatrix(visibleMap, radiusVisibility, reference.getX(), reference.getY(), '?');
        builder.append("  ");

        for (int dx = 0; dx < output[0].length; dx++)
            builder.append(String.format("%3d", dx + reference.getX() - radiusVisibility));

        builder.append("\n");

        for (int dy = 0; dy < output.length; dy++) {
            builder.append(String.format("%2d ", dy + reference.getY() - radiusVisibility));

            for (int dx = 0; dx < output[dy].length; dx++)
                builder.append(" " + output[dy][dx] + " ");

            builder.append("\n");
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }

    private void setSymbol(MapObject object, char representation) {
        Coordinate coordinate = object.getPosition();
        visibleMap[coordinate.getY()][coordinate.getX()] = representation;
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
