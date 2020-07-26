package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.GameImagesLoader;
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

public class GraphicGameViewer implements Renderable, MapViewer {
    private final Graphics2D graphics;
    private final char[][] output;
    private final int textWidth = 200;
    private final int textSpacing = 20;
    private HashMap<Character, ArrayList<BufferedImage>> images;
    private int cellHeight, cellWidth;
    private int frameImageCurrent, frameCounter, intervalChangeFrame;
    private volatile String messages = "";
    private volatile String status = "";
    private Map map;
    private volatile Coordinate reference;
    private JTextArea textMenu;
    private JTextArea textStatus;
    private ArrayList<Clickable> options;
    private volatile boolean needUpdateMap;
    private GraphicIO graphicIO;

    public GraphicGameViewer(Graphics2D graphics, GraphicEngine graphicEngine, Map map) {
        this.graphics = graphics;
        this.needUpdateMap = false;
        this.map = map;
        this.options = new ArrayList<>();
        this.graphicIO = new GraphicIO(graphicEngine.getKeyboardInput(), this);
        this.frameImageCurrent = this.frameCounter = 0;
        this.intervalChangeFrame = 20;

        GameImagesLoader gameImagesLoader = new GameImagesLoader();
        images = gameImagesLoader.getImages();

        output = new char[map.getHeight()][map.getWidth()];
        cellHeight = cellWidth = Math.min((GameWindow.WINDOW_WIDTH - 200) / (map.getWidth()), GameWindow.WINDOW_HEIGHT / (map.getHeight()));


        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++)
                options.add(new ClickableCell(this, i, j, cellHeight, cellWidth));

        textStatus = new JTextArea();
        textStatus.setSize(textWidth - textSpacing, GameWindow.WINDOW_HEIGHT / 7);
        textStatus.setLineWrap(true);
        textStatus.setWrapStyleWord(true);

        textMenu = new JTextArea();
        textMenu.setSize(textWidth - textSpacing, 6 * GameWindow.WINDOW_HEIGHT / 7);
        textMenu.setLineWrap(true);
        textMenu.setWrapStyleWord(true);

    }

    int getY(int i) {
        return cellHeight * i;
    }

    int getX(int i) {
        return cellWidth * i;
    }

    public void updateImageFrame() {
        frameCounter++;
        if (frameCounter == Integer.MAX_VALUE)
            frameCounter = 0;
        if (frameCounter % intervalChangeFrame == 0)
            frameImageCurrent++;
        if (frameImageCurrent == Integer.MAX_VALUE)
            frameImageCurrent = 0;
    }

    public void render() {
        updateImageFrame();
        renderBackGround();
        renderText();
        display(new Coordinate(0, 0));
    }

    private void renderText() {
        int spacingX = GameWindow.WINDOW_WIDTH - textWidth + textSpacing;

        graphics.translate(spacingX, textSpacing);
        textStatus.setText(status);
        textStatus.print(graphics);
        graphics.translate(-spacingX, -textSpacing);

        graphics.translate(spacingX, GameWindow.WINDOW_HEIGHT / 7);
        textMenu.setText(messages);
        textMenu.print(graphics);
        graphics.translate(-spacingX, -GameWindow.WINDOW_HEIGHT / 7);
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


    private boolean needUpImage(char c) {
        return c == 'W' || c == 'B' || c == 'E' || c == 'F' || c == 'S' || c == 'Ŝ' || c == 'G' || c == 'c' || c == 'C';
    }

    public BufferedImage upImage(BufferedImage image) {
        BufferedImage floor = images.get(' ').get(0);
        BufferedImage copyOfImage = new BufferedImage(cellWidth, cellHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(floor, 0, 0, cellWidth, cellHeight, null);
        g.drawImage(image, 0, cellHeight * -1 / 10, cellWidth, cellHeight, null);
        g.dispose();
        return copyOfImage;
    }

    public BufferedImage getFrame(char c) {
        ArrayList<BufferedImage> frames = images.get(c);
        return frames.get(frameImageCurrent % frames.size());
    }

    @Override
    public void display(Coordinate coordinate) {
        updateMap();
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[i].length; j++) {
                if (images.containsKey(output[i][j])) {
                    BufferedImage image = getFrame(output[i][j]);
                    graphics.drawImage(needUpImage(output[i][j]) ? upImage(image) : image, getX(j), getY(i), cellWidth, cellHeight, new Color(72, 59, 58), null);
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
                    graphics.fillRect(getX(j), getY(i), cellWidth, cellHeight);
                }
            }
    }

    private void renderBackGround() {
        if (graphics != null) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(GameWindow.WINDOW_WIDTH - textWidth, 0, textWidth, GameWindow.WINDOW_HEIGHT);
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

    public GraphicIO getGraphicIO() {
        return graphicIO;
    }
}