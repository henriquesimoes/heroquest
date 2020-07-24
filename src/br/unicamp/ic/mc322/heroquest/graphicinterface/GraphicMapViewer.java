package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
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

import java.awt.*;
import java.util.ArrayList;

public class GraphicMapViewer implements Renderable, MapViewer {
    private final ArrayList<Clickable> options;
    private final Settings SETTINGS;
    private final Graphics2D graphics;
    private final char[][] output;
    int cellHeight = 20, cellWidth = 20;
    Map map;

    public GraphicMapViewer(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager, Map map) {
        this.SETTINGS = settings;
        this.options = new ArrayList<>();
        this.graphics = graphics;
        this.map = map;

        output = new char[map.getHeight()][map.getWidth()];
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++)
                options.add(new CellMap(this, i, j, cellHeight, cellWidth));

        map.accept(this);
    }

    void changeState(int i, int j) {
        output[i][j] = 'a';
    }

    int getY(int i) {
        return cellHeight * i + cellHeight;
    }

    int getX(int i) {
        return cellWidth * i + cellWidth;
    }

    public void render() {
        renderBackGround();
        display(new Coordinate(0, 0));
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return new ArrayList<>(options);
    }

    @Override
    public void display(Coordinate coordinate) {
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++) {
                switch (output[i][j]) {
                    case '#':
                        graphics.setColor(new Color(97, 63, 44));
                        break;
                    case ' ':
                        graphics.setColor(new Color(100, 100, 100));
                        break;
                    case 'a':
                        graphics.setColor(new Color(255, 255, 0));
                        break;
                    default:
                        graphics.setColor(new Color(255, 255, 255));
                        break;
                }
                graphics.fillRect(getX(i), getY(j), getX(i + 1), getY(j + 1));
            }
    }

    private void renderBackGround() {
        if (graphics != null) {
            graphics.setColor(new Color(41, 43, 46));
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        } else {
            throw new NullPointerException();
        }
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