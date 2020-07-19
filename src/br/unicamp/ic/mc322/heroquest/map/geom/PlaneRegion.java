package br.unicamp.ic.mc322.heroquest.map.geom;

class PlaneRegion extends Region {
    private final Coordinate end;

    PlaneRegion(Coordinate start, Coordinate end) {
        super(start);
        this.end = end;
    }

    @Override
    public void build() {
        for (int x = reference.getX(); x < end.getX(); x++)
            for (int y = reference.getY(); y < end.getY(); y++)
                coordinates.add(new Coordinate(x, y));
    }
}
