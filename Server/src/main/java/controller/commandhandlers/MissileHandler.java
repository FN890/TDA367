package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles missile command.
 * Form: missile:x,y,rotation,playerXSpeed,playerYSpeed
 */
public class MissileHandler extends AbstractCommandHandler {

    public MissileHandler() {
        super("missile");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 5) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        try {

            float x = Float.parseFloat(args[0]);
            float y = Float.parseFloat(args[1]);
            float rotation = Float.parseFloat(args[2]);
            float playerXSpeed = Float.parseFloat(args[3]);
            float playerYSpeed = Float.parseFloat(args[4]);

            clientController.sendGameMissile(x, y, rotation, playerXSpeed, playerYSpeed);
        } catch (NumberFormatException e) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
        }
    }
}
