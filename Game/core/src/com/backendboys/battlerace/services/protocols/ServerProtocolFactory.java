package com.backendboys.battlerace.services.protocols;

/**
 * Factory for ServerProtocol.
 */
public class ServerProtocolFactory {

    /**
     * Get the ServerProtocol used for parsing messages into ICommand.
     *
     * @return IServerProtocol
     */
    public static IServerProtocol getServerProtocol() {
        return ServerProtocolFacade.getInstance();
    }

}
