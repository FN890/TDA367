package server.protocol;

class ServerProtocolFacade implements IServerProtocol {

    private static ServerProtocolFacade instance = null;

    private final ServerProtocolReader readerProtocol;
    private final ServerProtocolWriter writerProtocol;

    private ServerProtocolFacade() {
        this.readerProtocol = new ServerProtocolReader();
        this.writerProtocol = new ServerProtocolWriter();
    }

    @Override
    public synchronized Command parseTCPMessage(String msg) throws ProtocolException {
        Command cmd = readerProtocol.parseTCPMessage(msg);
        return readerProtocol.parseTCPCommand(cmd);
    }

    @Override
    public synchronized String writeError(String msg) {
        return writerProtocol.writeError(msg);
    }

    static synchronized ServerProtocolFacade getInstance() {
        if (instance == null) {
            instance = new ServerProtocolFacade();
        }
        return instance;
    }

}
