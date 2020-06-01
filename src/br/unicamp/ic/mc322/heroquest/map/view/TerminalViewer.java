package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;

public class TerminalViewer implements Viewer {

    public void display(Map map, MapObject reference) {
        StringBuilder builder = new StringBuilder();
        Dimension dimension = map.getDimension();

        builder.append("Displaying map...\n\n");

        Coordinate origin = Coordinate.getOrigin();

        for (int dy = 0; dy < dimension.getHeight(); dy++) {
            // TODO: encapsulate rows creation

            for (int dx = 0; dx < dimension.getWidth(); dx++) {
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
