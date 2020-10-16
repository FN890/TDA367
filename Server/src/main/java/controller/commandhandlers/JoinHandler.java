package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles Join Game Command.
 * Form: join:id,name
 */
public class JoinHandler extends AbstractHandler {

    public JoinHandler() {
        super("join");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 2) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        String id = args[0];
        String name = args[1];

        clientController.joinGame(name, id);
    }
}
