package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.engine.GameLoop;
import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.GameImagesLoader;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.map.MapPopulator;
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
    private final Settings SETTINGS;
    private final Graphics2D graphics;
    HashMap<Character, ArrayList<BufferedImage>> images;
    int cellHeight, cellWidth;
    int frame = 0;
    volatile String messages = "";
    volatile String status = "";
    Map map;
    volatile Coordinate reference;
    JTextArea textMenu;
    JTextArea textStatus;
    volatile boolean waitingCoordinate = false;
    volatile Coordinate clickedCoordinate;
    private ArrayList<Clickable> options;
    private char[][] output;
    private volatile boolean needUpdateMap;

    public GraphicMapViewer(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        this.SETTINGS = settings;
        this.graphics = graphics;
        this.needUpdateMap = false;

        GameImagesLoader gameImagesLoader = new GameImagesLoader();
        images = gameImagesLoader.getImages();

        textStatus = new JTextArea();
        textStatus.setSize(180, GameWindow.WINDOW_HEIGHT / 7);
        textStatus.setLineWrap(true);
        textStatus.setWrapStyleWord(true);

        textMenu = new JTextArea();
        textMenu.setSize(180, 6 * GameWindow.WINDOW_HEIGHT / 7);
        textMenu.setLineWrap(true);
        textMenu.setWrapStyleWord(true);

    }

    void changeState(int x, int y) {
        if (waitingCoordinate) {
            waitingCoordinate = false;
            clickedCoordinate = new Coordinate(x, y);
        }
    }

    public Coordinate getClickedCoordinate() {
        waitingCoordinate = true;
        while (waitingCoordinate) Thread.onSpinWait();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return clickedCoordinate;
    }

    int getY(int i) {
        return cellHeight * i;
    }

    int getX(int i) {
        return cellWidth * i;
    }

    public void render() {
        create();
        frame++;
        if (frame >= 20)
            frame = 0;
        renderBackGround();
        display(new Coordinate(0, 0));
        graphics.translate(GameWindow.WINDOW_WIDTH - 200, 0);
        textStatus.setText("");
        textStatus.print(graphics);

        graphics.translate(20, 0);
        textStatus.print(graphics);
        graphics.translate(0, 20);

        textStatus.setText(status);
        textStatus.print(graphics);
        graphics.translate(-GameWindow.WINDOW_WIDTH + 180, -20);

        graphics.translate(GameWindow.WINDOW_WIDTH - 200, GameWindow.WINDOW_HEIGHT / 7);
        textMenu.setText("");
        textMenu.print(graphics);

        graphics.translate(20, 0);
        textMenu.setText(messages);
        textMenu.print(graphics);
        graphics.translate(-GameWindow.WINDOW_WIDTH + 180, -GameWindow.WINDOW_HEIGHT / 7);
    }

    private void create() {
        if (SETTINGS.getMap().equals(map))
            return;

        map = SETTINGS.getMap();
        output = new char[map.getHeight()][map.getWidth()];
        cellHeight = cellWidth = Math.min((GameWindow.WINDOW_WIDTH - 200) / (map.getWidth()), GameWindow.WINDOW_HEIGHT / (map.getHeight()));

        this.options = new ArrayList<>();

        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++)
                options.add(new CellMap(this, i, j, cellHeight, cellWidth));

        map.add(SETTINGS.getWalker());
        MapPopulator populator = new MapPopulator(GameLevel.EASY);
        populator.populate(map);
        GameMonitor.getInstance().subscribe(map);
        Thread loop = new Thread(new GameLoop(map));
        loop.start();
    }

    public void updateMap() {
        if (needUpdateMap) {
            for (char[] chars : output)
                Arrays.fill(chars, '?');

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


    boolean upImage(char c) {
        return c == 'W' || c == 'B' || c == 'E' || c == 'F' || c == 'S' || c == 'Ŝ' || c == 'G' || c == 'c' || c == 'C';
    }

    @Override
    public void display(Coordinate coordinate) {
        updateMap();
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++) {
                if (images.containsKey(output[i][j])) {
                    if (upImage(output[i][j])) {
                        BufferedImage floor = images.get(' ').get(0);
                        BufferedImage copyOfImage = new BufferedImage(cellWidth, cellHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = copyOfImage.createGraphics();
                        g.drawImage(floor, 0, 0, cellWidth, cellHeight, null);
                        g.drawImage((images.get(output[i][j])).get(frame / 5), 0, cellHeight * -1 / 10, cellWidth, cellHeight, null);
                        g.dispose();
                        graphics.drawImage(copyOfImage, getX(j), getY(i), cellWidth, cellHeight, new Color(72, 59, 58), null);
                    } else
                        graphics.drawImage((images.get(output[i][j])).get(frame / 5), getX(j), getY(i), cellWidth, cellHeight, new Color(72, 59, 58), null);
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
        Coordinate coordinate = object.getPosition();
        output[coordinate.getY()][coordinate.getX()] = representation;
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