package controller;

import controller.commandhandlers.*;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;
import services.protocol.ServerProtocolFactory;

/**
 * Handles all incoming commands from the TCP- or UDPServer.
 */
class CommandManager {

    private final ClientController clientController;
    private final IServerProtocol protocol;

    private ICommandHandler firstHandler;

    /**
     * Initializes a CommandHandler.
     *
     * @param clientController The ClientController using this handler.
     */
    CommandManager(ClientController clientController) {
        this.clientController = clientController;
        protocol = ServerProtocolFactory.getServerProtocol();

        setupHandlers();
    }

    private void setupHandlers() {
        ICommandHandler udpPortHandler = new UDPPortHandler();
        ICommandHandler getHandler = new GetHandler();
        ICommandHandler createHandler = new CreateHandler();
        ICommandHandler joinHandler = new JoinHandler();
        ICommandHandler leaveHandler = new LeaveHandler();
        ICommandHandler startHandler = new StartHandler();
        ICommandHandler positionHandler = new PositionHandler();
        ICommandHandler missileHandler = new MissileHandler();

        udpPortHandler.setNext(getHandler);
        getHandler.setNext(createHandler);
        createHandler.setNext(joinHandler);
        joinHandler.setNext(leaveHandler);
        leaveHandler.setNext(startHandler);
        startHandler.setNext(positionHandler);
        positionHandler.setNext(missileHandler);

        firstHandler = udpPortHandler;
    }

    /**
     * Handles a command and forwards to ClientController.
     *
     * @param cmd The command to handle.
     */
    void handleCommand(ICommand cmd) {
        firstHandler.handle(cmd, clientController, protocol);
    }

}
