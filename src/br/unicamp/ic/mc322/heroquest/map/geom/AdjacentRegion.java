package br.unicamp.ic.mc322.heroquest.map.geom;

class AdjacentRegion extends Region {
    AdjacentRegion(Coordinate reference) {
        super(reference);
    }

    @Override
    protected void build() {
        Coordinate[] neighbors = reference.getAdjacentNeighborCoordinates();

        for (Coordinate neighbor : neighbors)
            if (isValid(neighbor))
                coordinates.add(neighbor);
    }
}
