package server.protocol;

public interface IServerProtocol {
    Command parseTCPMessage(String msg) throws ProtocolException;
    String writeError(String msg);
}
