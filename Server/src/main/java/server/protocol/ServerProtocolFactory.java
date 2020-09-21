package server.protocol;

public class ServerProtocolFactory {

    public static IServerProtocol getServerProtocol() {
        return ServerProtocolFacade.getInstance();
    }

}
