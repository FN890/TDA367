package controller;

import services.protocol.ICommand;
import services.protocol.IServerProtocol;
import services.protocol.ServerProtocolFactory;

class CommandHandler {

    private final ClientController clientController;
    private final IServerProtocol protocol;

    CommandHandler(ClientController clientController) {
        this.clientController = clientController;
        protocol = ServerProtocolFactory.getServerProtocol();
    }

    void handleCommand(ICommand cmd) {
        switch (cmd.getCmd()) {
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

    private void handleCreateGame(String[] args) {
        if (args.length < 1) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }
        String name = args[0];
        clientController.createGame(name);
    }

    private void handleJoinGame(String[] args) {
        if (args.length < 2) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        String id = args[0];
        String name = args[1];

        clientController.joinGame(name, id);
    }

    private void handleLeaveGame() {
        clientController.leaveGame();
    }


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
