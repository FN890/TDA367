package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Interface in Chain of Responsibility pattern controlling execution of commands.
 */
public interface ICommandHandler {

    /**
     * Sets the next ICommandHandler in the Chain of Responsibility pattern.
     *
     * @param handler The next handler.
     */
    void setNext(ICommandHandler handler);

    /**
     * Handles a specific command issued from a ClientController.
     *
     * @param cmd              The command to handle.
     * @param clientController The ClientController.
     * @param protocol         The Protocol.
     */
    void handle(ICommand cmd, ClientController clientController, IServerProtocol protocol);
}
