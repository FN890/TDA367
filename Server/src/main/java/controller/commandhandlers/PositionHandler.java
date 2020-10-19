package controller.commandhandlers;

import controller.ClientController;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;

/**
 * Handles Update Position Command.
 * Form: pos:x,y,rotation
 */
public class PositionHandler extends AbstractCommandHandler {

    public PositionHandler() {
        super("pos");
    }

    @Override
    protected void concreteHandle(ICommand cmd, ClientController clientController, IServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 3) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
            return;
        }

        try {
            float x = Float.parseFloat(args[0]);
            float y = Float.parseFloat(args[1]);
            float rotation = Float.parseFloat(args[2]);

            clientController.updateGamePosition(x, y, rotation);

        } catch (NumberFormatException e) {
            clientController.sendTCP(protocol.writeError("Invalid arguments."));
        }



    }
}
