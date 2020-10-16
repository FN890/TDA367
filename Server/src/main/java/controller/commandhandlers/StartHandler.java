package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles Start Game Command.
 * Form: start
 */
public class StartHandler extends AbstractHandler {

    public StartHandler() {
        super("start");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        clientController.startGame();
    }
}
