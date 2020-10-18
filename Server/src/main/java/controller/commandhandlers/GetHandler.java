package controller.commandhandlers;

import controller.ClientController;
import model.Game;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles all get commands.
 * Form: get:command.
 * Sends a response to the client. Either response: or error:
 */
public class GetHandler extends AbstractCommandHandler {

    public GetHandler() {
        super("get");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        Game game = clientController.getGame();
        if (game == null) {
            clientController.sendTCP(protocol.writeError("Not in a game."));
            return;
        }

        switch (args[0]) {

            case "game":
                clientController.sendTCP(protocol.writeGameInfo(game));
                break;

        }
    }
}
