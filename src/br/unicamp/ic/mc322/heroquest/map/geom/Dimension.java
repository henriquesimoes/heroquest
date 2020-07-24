package br.unicamp.ic.mc322.heroquest.map.geom;

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
     *
     * @param position Position to be included
     * @return New dimension
     */
    public Dimension fit(Coordinate position) {
        return new Dimension(
                Math.max(position.getX() + 1, this.getWidth()),
                Math.max(position.getY() + 1, this.getHeight()));
    }

    /**
     * Creates a coordinate that corresponds to the end coordinate
     * on the plane by using the origin.
     *
     * @return coordinate representation of the dimension
     */
    public Coordinate toCoordinate() {
        return Coordinate.shift(Coordinate.getOrigin(), width, height);
    }
}
