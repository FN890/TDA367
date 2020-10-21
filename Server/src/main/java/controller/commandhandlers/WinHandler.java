package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles the win command.
 * Form: win
 */
public class WinHandler extends AbstractCommandHandler {

    public WinHandler() {
        super("win");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        clientController.sendWin();
    }
}
