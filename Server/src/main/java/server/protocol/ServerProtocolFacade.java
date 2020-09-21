package server.protocol;

class ServerProtocolFacade {

    private static ServerProtocolFacade instance = null;

    private ServerProtocolFacade() {}

    Command parseTCPMessage(String msg) throws ProtocolException {
        Command cmd = ServerProtocol.parseTCPMessage(msg);
        return ServerProtocol.parseTCPCommand(cmd);
    }

    static ServerProtocolFacade getInstance() {
        if (instance == null) {
            instance = new ServerProtocolFacade();
        }
        return instance;
    }

}
