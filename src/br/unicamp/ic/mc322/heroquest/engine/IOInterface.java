package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;

/**
 * Defines the way of interacting with the user by input and output messages.
 */
public interface IOInterface {
    /**
     * Shows a list of options to the user and returns the index of the answer.
     *
     * @param options   - list of string options
     * @param allowBack - whether is it allowed not to answer and get back
     * @return 0 for the back command, and 1-based index of the choice otherwise
     */
    int showOptionsAndGetAnswer(String[] options, boolean allowBack);

    /**
     * Shows a list of commands to the user and executes the selected one
     *
     * @param commands  - list of commands
     * @param allowBack - whether it is allowed not to execute a command
     */
    void selectAndExecute(Command[] commands, boolean allowBack);

    /**
     * Displays an ordinary message to the user.
     *
     * @param message - content
     */
    void showMessage(String message);

    /**
     * Warns the user that a error has occurred.
     *
     * @param message - error message
     */
    void showError(String message);

    /**
     * Displays the question to the player and returns their answer.
     *
     * @param question - boolean answerable question
     * @return answer
     */
    boolean getBooleanAnswer(String question);

    /**
     * Triggers the map display event
     *
     * @param position - point of view coordinate to use in the map display.
     *                 The point of view is used to define the visible region of the map
     *                 to be displayed.
     */
    void showMap(Coordinate position);

    void showStatus(String message);

    /**
     * Defines the map to be display along with the messages
     *
     * @param map - new map to be displayed
     */
    void setMap(Map map);

    /**
     * Requests the user a direction in which he or she would like to move their character
     *
     * @return chosen direction
     */
    Direction getMoveDirection();

    /**
     * Requests the user to select a coordinate among the given ones.
     *
     * @param coordinates - all possible coordinates
     * @return selected coordinate
     */
    Coordinate getCoordinate(Coordinate[] coordinates);
}
