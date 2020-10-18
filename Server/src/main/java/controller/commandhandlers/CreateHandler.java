package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles Create Game Command.
 * Form: create:name
 */
public class CreateHandler extends AbstractCommandHandler {

    public CreateHandler() {
        super("create");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }
        String name = args[0];
        clientController.createGame(name);
    }
}
