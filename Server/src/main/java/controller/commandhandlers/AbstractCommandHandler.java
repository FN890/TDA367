package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * AbstractHandler handles the next in Chain of Responsibility pattern,
 * and the check for each command.
 */
abstract class AbstractCommandHandler implements ICommandHandler {

    private ICommandHandler next;
    private String command;

    protected AbstractCommandHandler(String command) {
        this.command = command;
    }

    @Override
    public void setNext(ICommandHandler handler) {
        next = handler;
    }

    @Override
    public void handle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        if (!cmd.getCmd().equalsIgnoreCase(command)) {
            if (next != null) {
                next.handle(cmd, clientController, protocol);
            }
            return;
        }

        concreteHandle(cmd, clientController, protocol);
    }

    /**
     * Specifies a concrete implementation of a command handler method.
     *
     * @param cmd              The command to handle.
     * @param clientController The ClientController issuing the command.
     * @param protocol         The protocol used for handling the command.
     */
    protected abstract void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol);

}
