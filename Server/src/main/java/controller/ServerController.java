package controller;

import server.TCPServer;
import server.UDPServer;

import java.io.IOException;

public class ServerController {

    private static final int PORT = 26000;

    private final TCPServer tcpServer;
    private final UDPServer udpServer;

    public ServerController() {
        udpServer = createUDPServer();
        tcpServer = createTCPServer();
    }

    private UDPServer createUDPServer() {
        try {
            UDPServer server = new UDPServer(PORT);
            new Thread(server).start();
            return server;
        } catch (IOException ignored) {}

        System.out.println("ERROR: Could not create TCP Server.");
        return null;
    }

    private TCPServer createTCPServer() {
        try {
            TCPServer server = new TCPServer(PORT);
            server.listen();
            return server;
        } catch (IOException ignored) {}

        System.out.println("ERROR: Could not create TCP Server.");
        return null;
    }

}
