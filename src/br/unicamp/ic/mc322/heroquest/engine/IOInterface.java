package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;

public interface IOInterface {
    int showOptionsAndGetAnswer(String[] options, boolean allowBack);

    void selectAndExecute(Command[] commands, boolean allowBack);

    void showMessage(String message);

    void showError(String message);

    String getStringAnswer(String question);

    void showMap(Coordinate position);

    void showStatus(String message);

    void setMap(Map map);

    Direction getMoveDirection();

    Coordinate getCoordinate(Coordinate[] coordinates);
}
