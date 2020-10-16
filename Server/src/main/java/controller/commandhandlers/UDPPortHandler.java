package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

public class UDPPortHandler extends AbstractHandler {

    public UDPPortHandler() {
        super("port");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            clientController.sendTCP(protocol.writeError("Invalid port."));
            return;
        }

        try {
            int port = Integer.parseInt(args[0]);
            clientController.listenForUDPFromPort(port);

        } catch (NumberFormatException e) {
            clientController.sendTCP(protocol.writeError("Invalid port."));
        }

    }
}
