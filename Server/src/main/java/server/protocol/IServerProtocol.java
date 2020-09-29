package server.protocol;

import data.Vector2;

public interface IServerProtocol {
    Command parseTCPMessage(String msg) throws ProtocolException;
    String writeError(String msg);
    String writePosition(String name, Vector2 pos);
}
