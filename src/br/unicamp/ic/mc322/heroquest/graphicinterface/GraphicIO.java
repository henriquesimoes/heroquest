package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.view.Command;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;

public class GraphicIO implements IOInterface {
    @Override
    public int showOptionsAndGetAnswer(String[] options, boolean allowBack) {
        return 0;
    }

    @Override
    public void selectAndExecute(Command[] commands, boolean allowBack) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public String getStringAnswer(String question) {
        return null;
    }

    @Override
    public void showMap(Coordinate position) {

    }

    @Override
    public void setMap(Map map) {

    }

    @Override
    public Direction getMoveDirection() {
        return null;
    }

    @Override
    public Coordinate getCoordinate(Coordinate[] coordinates) {
        return null;
    }
}
