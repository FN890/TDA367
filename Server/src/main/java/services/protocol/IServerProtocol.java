package services.protocol;

import data.Vector2;
import model.Game;

/**
 * IServerProtocol used for parsing message into ICommand and correctly writing messages.
 */
public interface IServerProtocol {
    /**
     * Parses a message into ICommand containing a command, and a number of arguments
     * on the form: command:arg1,arg2,arg3...
     *
     * @param msg The message to parse.
     * @return ICommand containing the command and a number of arguments.
     * @throws ProtocolException When the message is on the wrong form.
     */
    Command parseMessage(String msg) throws ProtocolException;

    /**
     * Writes a response on the form: response:message
     *
     * @param msg The response message.
     * @return The message on the form response:message
     */
    String writeResponse(String msg);

    /**
     * Writes an error onto the form: error:message
     *
     * @param msg The error message.
     * @return The message on the form error:message
     */
    String writeError(String msg);

    /**
     * Writes an objects position on the form: pos:name,x,y,rotation
     *
     * @param name     The name who has the position.
     * @param pos      The position as a Vector2 containing x,y values.
     * @param rotation The rotation as a float.
     * @return The position on the form: pos:name,x,y,rotation
     */
    String writePosition(String name, Vector2 pos, float rotation);

    /**
     * Writes a missile on the form: missile:x,y,rotation,playerSpeed
     *
     * @param x The x spawn position of the missile.
     * @param y The y spawn position of the missile.
     * @param rotation The rotation of the missile.
     * @param playerXSpeed The player's speed in x-axis, sending the missile.
     * @param playerYSpeed The player's speed in y-axis, sending the missile.
     * @return The missile on the form: missile:x,y,rotation,playerSpeed
     */
    String writeMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed);

    /**
     * Writes info about the game on the form: response:id,isRunning,name1,name2...
     *
     * @param game The game to write.
     * @return The game on the form: response:id,isRunning,name1,name2...
     */
    String writeGameInfo(Game game);

    /**
     * Writes Game update about Players joining or leaving a game.
     *
     * @param name   The Player that's updated.
     * @param joined Specifies weather the player joined or left the game.
     * @return The update on the form: game:joined,name / game:left,name
     */
    String writeGamePlayerUpdate(String name, boolean joined);

    /**
     * Writes game winner.
     *
     * @param name The winner's name.
     * @return The winner on the form: game:winner,name
     */
    String writeGamePlayerWinner(String name);

    /**
     * Writes game status updates.
     *
     * @param started Specifies weather the game is running or not.
     * @return The update on the form: game:started / game:paused
     */
    String writeGameStatusUpdated(boolean started);

    /**
     * Writes game ended update.
     *
     * @return The update on the form: game:ended
     */
    String writeGameEndedUpdate();

}
