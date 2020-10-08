package controller;

import model.Game;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;
import services.protocol.ServerProtocolFactory;

/**
 * Handles all incoming commands from the TCP- or UDPServer.
 */
class CommandHandler {

    private final ClientController clientController;
    private final IServerProtocol protocol;

    /**
     * Initializes a CommandHandler.
     *
     * @param clientController The ClientController using this handler.
     */
    CommandHandler(ClientController clientController) {
        this.clientController = clientController;
        protocol = ServerProtocolFactory.getServerProtocol();
    }

    /**
     * Handles a command and forwards to ClientController.
     *
     * @param cmd The command to handle.
     */
    void handleCommand(ICommand cmd) {
        switch (cmd.getCmd()) {
            case "get":
                handleGet(cmd.getArgs());
                break;
            case "create":
                handleCreateGame(cmd.getArgs());
                break;
            case "join":
                handleJoinGame(cmd.getArgs());
                break;
            case "leave":
                handleLeaveGame();
                break;
            case "pos":
                handleUpdatePos(cmd.getArgs());
                break;
        }
    }

    /**
     * Handles all get commands.
     * Sends a response to the client. Either response: or error:
     *
     * @param args The arguments of the get: command.
     */
    private void handleGet(String[] args) {
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

    /**
     * Handles Create Game Command.
     * Form: create:name
     *
     * @param args The commands arguments.
     */
    private void handleCreateGame(String[] args) {
        if (args.length < 1) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }
        String name = args[0];
        clientController.createGame(name);
    }

    /**
     * Handles Join Game Command.
     * Form: join:id,name
     *
     * @param args The commands arguments.
     */
    private void handleJoinGame(String[] args) {
        if (args.length < 2) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        String id = args[0];
        String name = args[1];

        clientController.joinGame(name, id);
    }

    /**
     * Handles Leave Game Command.
     * Form: leave
     */
    private void handleLeaveGame() {
        clientController.leaveGame();
    }

    /**
     * Handles Update Position Command.
     * Form: pos:x,y,rotation
     *
     * @param args The commands arguments.
     */
    private void handleUpdatePos(String[] args) {
        if (args.length < 3) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        float x = Float.parseFloat(args[0]);
        float y = Float.parseFloat(args[1]);
        float rotation = Float.parseFloat(args[2]);

        clientController.updateGamePosition(x, y, rotation);
    }

}
