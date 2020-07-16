package br.unicamp.ic.mc322.heroquest.map.geom;

class CardinalRegion extends Region {
    CardinalRegion(Coordinate reference) {
        super(reference);
    }

    @Override
    protected void build() {
        Coordinate[] neighbors = reference.getCardinalNeighborCoordinates();

        for (Coordinate neighbor : neighbors)
            if (isValid(neighbor))
                coordinates.add(neighbor);
    }
}
