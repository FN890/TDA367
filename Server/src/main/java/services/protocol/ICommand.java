package services.protocol;

/**
 * ICommand represents a command parsed from a message. Retrieved from IServerProtocol.
 */
public interface ICommand {

    /**
     * @return The command of type string.
     */
    String getCmd();

    /**
     * @return An string array of arguments.
     */
    String[] getArgs();

}
