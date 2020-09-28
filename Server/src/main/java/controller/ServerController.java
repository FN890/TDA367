package controller;

import server.TCPServer;

public class ServerController {

    private static final int PORT = 26000;

    private final TCPServer tcpServer;

    public ServerController() {
        tcpServer = createTCPServer();
    }

    private TCPServer createTCPServer() {
        try {
            TCPServer server = new TCPServer(PORT);
            server.listen();
            return server;
        } catch (Exception ignored) {}

        System.out.println("ERROR: Could not create TCP Server.");
        return null;
    }

}
