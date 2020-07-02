package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.fixed.Chest;
import br.unicamp.ic.mc322.heroquest.map.object.structural.*;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.hero.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Elf;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monster.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monster.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monster.SkeletonWizard;

public class TerminalViewer implements Viewer {
    private Map map;
    private char output[][];

    public TerminalViewer(Map map) {
        this.map = map;

        output = new char[map.getHeight()][map.getWidth()];
    }

    @Override
    public void display(MapObject reference) {
        clear();
        map.accept(this);
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
    public void visit(SkeletonWizard skeletonWizard) {
        setSymbol(skeletonWizard, 'Ŝ');
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
