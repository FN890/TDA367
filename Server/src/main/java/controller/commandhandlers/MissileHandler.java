package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

public class MissileHandler extends AbstractCommandHandler {

    public MissileHandler() {
        super("missile");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 4) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        try {

            float x = Float.parseFloat(args[0]);
            float y = Float.parseFloat(args[1]);
            float rotation = Float.parseFloat(args[2]);
            float playerSpeed = Float.parseFloat(args[3]);

            clientController.sendGameMissile(x, y, rotation, playerSpeed);
        } catch (NumberFormatException e) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
        }
    }
}
