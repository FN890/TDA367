package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles Leave Game Command.
 * Form: leave
 */
public class LeaveHandler extends AbstractCommandHandler {

    public LeaveHandler() {
        super("leave");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        clientController.leaveGame();
    }
}
