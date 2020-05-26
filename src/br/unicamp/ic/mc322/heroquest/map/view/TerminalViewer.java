package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;

public class TerminalViewer implements Viewer {

    public void display(Map map, MapObject reference) {
        StringBuilder builder = new StringBuilder();
        Dimension dimension = map.getDimension();

        builder.append("Displaying map...\n\n");

        Coordinate origin = Coordinate.getOrigin();

        for (int dx = 0; dx < dimension.getHeight(); dx++) {
            // TODO: encapsulate rows creation

            for (int dy = 0; dy < dimension.getWidth(); dy++) {
                Coordinate coor = Coordinate.shift(origin, dx, dy);

                ObjectView view = map.getStructureRepresentationAt(coor);

                builder.append(view);
            }

            // TODO: encapsulate row wrap-up
            builder.append("\n");
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }
}
