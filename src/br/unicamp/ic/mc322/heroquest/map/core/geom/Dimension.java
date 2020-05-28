package br.unicamp.ic.mc322.heroquest.map.core.geom;

public class Dimension {
    private int width;
    private int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Creates a new dimension which includes the given position, based
     * on this dimension.
     * @param position Position to be included
     * @return New dimension
     */
    public Dimension fit(Coordinate position) {
        return new Dimension(
                Math.max(position.getX() + 1, this.getWidth()),
                Math.max(position.getY() + 1, this.getHeight()));
    }
}
