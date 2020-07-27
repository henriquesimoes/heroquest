package br.unicamp.ic.mc322.heroquest.map.geom;

/**
 * Squared region of the place. This region simplifies the iteration
 * over a map, reducing the need of nested loops over coordinates.
 */
class PlaneRegion extends Region {
    private final Coordinate end;

    PlaneRegion(Coordinate start, Coordinate end) {
        super(start);
        this.end = end;
    }

    @Override
    protected void build() {
        for (int x = reference.getX(); x < end.getX(); x++)
            for (int y = reference.getY(); y < end.getY(); y++)
                coordinates.add(new Coordinate(x, y));
    }
}
