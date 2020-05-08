package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;

public class Viewer {
    private Map map;

    public Viewer(Map map) {
        this.map = map;
    }

    public void display() {
        StringBuilder builder = new StringBuilder();
        Dimension dimension = map.getDimension();

        builder.append("Displaying map...\n\n");

        for (int i = 0; i < dimension.getHeight(); i++) {
            // TODO: encapsulate rows creation

            for (int j = 0; j < dimension.getWidth(); j++) {
                Coordinate coor = new Coordinate(j, i);

                ObjectView view = map.get(coor).getRepresentation();

                builder.append(view);
            }

            // TODO: encapsulate row wrap-up
            builder.append("\n");
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }
}
