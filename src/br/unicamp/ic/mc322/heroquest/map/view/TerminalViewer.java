package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.OutsideRoomException;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

public class TerminalViewer implements Viewer {

    public void display(Map map, MapObject reference) {
        StringBuilder builder = new StringBuilder();
        Dimension dimension = map.getDimension();

        Coordinate origin = Coordinate.getOrigin();

        builder.append("  ");
        for (int dx = 0; dx < dimension.getWidth(); dx++)
            builder.append(String.format("%3d", dx));
        builder.append("\n");

        for (int dy = 0; dy < dimension.getHeight(); dy++) {
            // TODO: encapsulate rows creation
            builder.append(String.format("%2d ", dy));

            for (int dx = 0; dx < dimension.getWidth(); dx++) {
                Coordinate coordinate = Coordinate.shift(origin, dx, dy);

                /*MapObject object = map.getPreferentialObject(coordinate);
                ObjectView view = object.getRepresentation();

                builder.append(" " + view + " ");*/
            }

            // TODO: encapsulate row wrap-up
            builder.append("\n");
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }
}
