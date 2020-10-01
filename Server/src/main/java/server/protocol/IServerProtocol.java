package server.protocol;

import data.Vector2;

/**
 * IServerProtocol used for parsing message into ICommand and correctly writing messages.
 */
public interface IServerProtocol {
    /**
     * Parses a message into ICommand containing a command, and a number of arguments
     * on the form: command:arg1,arg2,arg3...
     * @param msg The message to parse.
     * @return ICommand containing the command and a number of arguments.
     * @throws ProtocolException When the message is on the wrong form.
     */
    Command parseMessage(String msg) throws ProtocolException;

    /**
     * Writes an error onto the form: error:message
     * @param msg The error message.
     * @return The message on the form error:message
     */
    String writeError(String msg);

    /**
     * Writes an objects position on the form: pos:name,x,y,rotation
     * @param name The name who has the position.
     * @param pos The position as a Vector2 containing x,y values.
     * @param rotation The rotation as a float.
     * @return The position on the form: pos:name,x,y,rotation
     */
    String writePosition(String name, Vector2 pos, float rotation);
}
