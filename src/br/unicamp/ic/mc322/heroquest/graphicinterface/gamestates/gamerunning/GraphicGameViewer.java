package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.MapViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.GameImagesLoader;
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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GraphicGameViewer implements StateViewer, MapViewer {
    private final Graphics2D graphics;
    private final RenderableObject[][] mapMatrix;
    private final int textWidth = 200;
    private final int textSpacing = 20;
    private final int RADIUS;
    private final HashMap<RenderableObject, ArrayList<BufferedImage>> images;
    private RenderableObject[][] matrixOut;
    private int cellHeight, cellWidth;
    private int frameCounter;
    private Map map;
    private JTextArea textMenu, textStatus;
    private ArrayList<Clickable> options;
    private GraphicIO graphicIO;
    private volatile String messages, status;
    private volatile Coordinate reference;
    private volatile boolean needUpdateMap;

    public GraphicGameViewer(Graphics2D graphics, GamePanel gamePanel, Map map) {
        this.RADIUS = VisibleRegion.MAXIMUM_VISIBILITY_RADIUS;
        this.reference = new Coordinate(RADIUS, RADIUS);
        this.graphics = graphics;
        this.needUpdateMap = true;
        this.map = map;
        this.options = new ArrayList<>();
        this.graphicIO = new GraphicIO(gamePanel.getKeyboardInput(), this);
        this.frameCounter = 0;
        this.messages = this.status = "";

        GameImagesLoader gameImagesLoader = new GameImagesLoader();
        images = gameImagesLoader.getImages();

        mapMatrix = new RenderableObject[map.getHeight()][map.getWidth()];

        matrixOut = new RenderableObject[2 * RADIUS + 1][2 * RADIUS + 1];
        cellHeight = cellWidth = Math.min((GameWindow.WINDOW_WIDTH - 200) / matrixOut.length, GameWindow.WINDOW_HEIGHT / matrixOut.length);


        for (int i = 0; i < matrixOut.length; i++)
            for (int j = 0; j < matrixOut[i].length; j++)
                options.add(new ClickableCell(this, gamePanel, i, j, cellHeight, cellWidth));

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
    }

    public void render() {
        updateImageFrame();
        renderBackGround();
        renderText();
        display(reference);
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
            for (RenderableObject[] objects : mapMatrix)
                Arrays.fill(objects, RenderableObject.UNKNOWN);

            RegionSelector regionSelector = map.getRegionSelector();
            Region region = regionSelector.getVisibleRegion(reference);
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

    public BufferedImage shiftImageUp(BufferedImage image) {
        BufferedImage floor = images.get(RenderableObject.FLOOR).get(0);
        BufferedImage copyOfImage = new BufferedImage(cellWidth, cellHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(floor, 0, 0, cellWidth, cellHeight, null);
        g.drawImage(image, 0, cellHeight * -1 / 10, cellWidth, cellHeight, null);
        g.dispose();
        return copyOfImage;
    }

    public BufferedImage getFrame(RenderableObject object) {
        ArrayList<BufferedImage> frames = images.get(object);
        return frames.get(frameCounter % frames.size());
    }

    @Override
    public void display(Coordinate coordinate) {
        updateMap();
        matrixOut = getCentralizedMatrix(coordinate);

        for (int i = 0; i < matrixOut.length; i++)
            for (int j = 0; j < matrixOut[i].length; j++) {
                BufferedImage image = getFrame(matrixOut[i][j]);
                graphics.drawImage(matrixOut[i][j].needsToShiftImageUp() ? shiftImageUp(image) : image,
                            getX(j), getY(i), cellWidth, cellHeight, new Color(72, 59, 58), null);
            }
    }

    private void renderBackGround() {
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(GameWindow.WINDOW_WIDTH - textWidth, 0, textWidth, GameWindow.WINDOW_HEIGHT);
    }

    private void setSymbol(MapObject object, RenderableObject render) {
        Coordinate coordinate = object.getPosition();
        mapMatrix[coordinate.getY()][coordinate.getX()] = render;
    }

    /**
     * Returns a map representation centralized on the given reference point.
     *
     * @param reference center point
     * @return
     */
    private RenderableObject[][] getCentralizedMatrix(Coordinate reference) {
        RenderableObject[][] result = new RenderableObject[2 * RADIUS + 1][2 * RADIUS + 1];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                int x = reference.getX() - RADIUS + j;
                int y = reference.getY() - RADIUS + i;
                if (x >= 0 && y >= 0 && x < mapMatrix[0].length && y < mapMatrix.length)
                    result[i][j] = mapMatrix[y][x];
                else
                    result[i][j] = RenderableObject.UNKNOWN;
            }
        }
        return result;
    }

    @Override
    public void visit(Barbarian barbarian) {
        setSymbol(barbarian, RenderableObject.BARBARIAN);
    }

    @Override
    public void visit(Dwarf dwarf) {
        setSymbol(dwarf, RenderableObject.DWARF);
    }

    @Override
    public void visit(Elf elf) {
        setSymbol(elf, RenderableObject.ELF);
    }

    @Override
    public void visit(Wizard wizard) {
        setSymbol(wizard, RenderableObject.WIZARD);
    }

    @Override
    public void visit(WizardSkeleton wizardSkeleton) {
        setSymbol(wizardSkeleton, RenderableObject.WIZARD_SKELETON);
    }

    @Override
    public void visit(CommonSkeleton commonSkeleton) {
        setSymbol(commonSkeleton, RenderableObject.COMMON_SKELETON);
    }

    @Override
    public void visit(Goblin goblin) {
        setSymbol(goblin, RenderableObject.GOBLIN);
    }

    @Override
    public void visit(Floor floor) {
        setSymbol(floor, RenderableObject.FLOOR);
    }

    @Override
    public void visit(Wall wall) {
        setSymbol(wall, RenderableObject.WALL);
    }

    @Override
    public void visit(Door door) {
        setSymbol(door, door.isOpen() ? RenderableObject.FLOOR : RenderableObject.DOOR);
    }

    @Override
    public void visit(SecretDoor secretDoor) {
        if (secretDoor.isDiscovered())
            visit((Door) secretDoor);
        else
            setSymbol(secretDoor, RenderableObject.WALL);
    }

    @Override
    public void visit(Chest chest) {
        setSymbol(chest, chest.isOpened() ? RenderableObject.CHEST_OPEN : RenderableObject.CHEST_CLOSE);
    }

    @Override
    public void visit(Trap trap) {
        setSymbol(trap, trap.isDiscovered() ? (
                trap.isArmed() ? RenderableObject.TRAP_ARMED : RenderableObject.TRAP_UNARMED) : RenderableObject.FLOOR);
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

    /**
     * Converts the screen relative coordinate to an map coordinate.
     *
     * @param coordinate - screen relative coordinate
     * @return map absolute coordinate
     */
    Coordinate convertToMapRelative(Coordinate coordinate) {
        return Coordinate.shift(reference, -RADIUS + coordinate.getX(), -RADIUS + coordinate.getY());
    }
}
