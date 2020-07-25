package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
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
import br.unicamp.ic.mc322.heroquest.walker.heroes.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Dwarf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Elf;
import br.unicamp.ic.mc322.heroquest.walker.heroes.Wizard;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GraphicMapViewer implements Renderable, MapViewer {
    private final ArrayList<Clickable> options;
    private final Settings SETTINGS;
    private final Graphics2D graphics;
    private final char[][] output;
    HashMap<Character, ArrayList<BufferedImage>> images;
    int cellHeight, cellWidth;
    int frame = 0;
    volatile String messages = "";
    volatile String status = "";
    Map map;
    Coordinate reference;
    JTextArea textMenu;
    JTextArea textStatus;

    private volatile boolean needUpdateMap;

    public GraphicMapViewer(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager, Map map) {
        this.SETTINGS = settings;
        this.options = new ArrayList<>();
        this.graphics = graphics;
        this.map = map;
        this.needUpdateMap = false;
        LoaderImages loaderImages = new LoaderImages();
        images = loaderImages.getImages();
        cellHeight = cellWidth = Math.min((GameWindow.WINDOW_WIDTH - 200) / (map.getWidth()), GameWindow.WINDOW_HEIGHT / (map.getHeight()));

        output = new char[map.getHeight()][map.getWidth()];
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++)
                options.add(new CellMap(this, j, i, cellHeight, cellWidth));

        textMenu = new JTextArea();
        textMenu.setSize(200, GameWindow.WINDOW_HEIGHT / 2);
        textMenu.setLineWrap(true);
        textMenu.setWrapStyleWord(true);

        textStatus = new JTextArea();
        textStatus.setSize(200, GameWindow.WINDOW_HEIGHT / 8);
        textStatus.setLineWrap(true);
        textStatus.setWrapStyleWord(true);
    }

    void changeState(int i, int j) {
        output[i][j] = 'a';
    }

    int getY(int i) {
        return cellHeight * i;
    }

    int getX(int i) {
        return cellWidth * i;
    }

    public void render() {
        frame++;
        if(frame >= 20)
            frame = 0;
        renderBackGround();
        display(new Coordinate(0, 0));
        graphics.translate(GameWindow.WINDOW_WIDTH - 200, 0);
        textStatus.setText(status);
        textStatus.print(graphics);
        graphics.translate(-GameWindow.WINDOW_WIDTH + 200, 0);

        graphics.translate(GameWindow.WINDOW_WIDTH - 200, GameWindow.WINDOW_HEIGHT / 8);
        textMenu.setText(messages);
        textMenu.print(graphics);
        graphics.translate(-GameWindow.WINDOW_WIDTH + 200, -GameWindow.WINDOW_HEIGHT / 8);
    }

    public void updateMap() {
        if (needUpdateMap) {
            for (int i = 0; i < output.length; i++)
                Arrays.fill(output[i], '?');
            RegionSelector regionSelector = map.getRegionSelector();
            Region region = regionSelector.getVisibleRegion(reference, false);
            map.accept(this, region);
            needUpdateMap = false;
        }
    }

    public void setNeedUpdateMap(Coordinate reference) {
        this.reference = reference;
        needUpdateMap = true;
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return new ArrayList<>(options);
    }

    @Override
    public void display(Coordinate coordinate) {
        updateMap();
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++) {
                if (images.containsKey(output[i][j])) {
                    graphics.drawImage(images.get(output[i][j]).get(frame/5), getX(j), getY(i), cellWidth, cellHeight, new Color(72, 59, 58), null);
                } else {
                    switch (output[i][j]) {
                        case ' ':
                            graphics.setColor(new Color(72, 59, 58));
                            break;
                        case '?':
                            graphics.setColor(new Color(0, 0, 0));
                            break;
                        default:
                            graphics.setColor(new Color(255, 255, 255));
                            break;
                    }
                    graphics.fillRect(getX(j), getY(i), getX(j + 1), getY(i + 1));
                }
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

    public void appendMessage(String s) {
        messages += s;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void clear() {
        messages = "";
    }
}