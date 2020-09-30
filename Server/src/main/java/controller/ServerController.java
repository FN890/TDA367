package controller;

import server.PacketListener;
import server.TCPServer;
import server.UDPServer;

import java.io.IOException;
import java.net.InetAddress;

public class ServerController {

    private static final int PORT = 26000;
    private static ServerController instance = null;

    private final TCPServer tcpServer;
    private final UDPServer udpServer;

    private ServerController() {
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

    public void addPacketListener(InetAddress address, PacketListener listener) {
        udpServer.addListener(address, listener);
    }

    public void removePacketListener(InetAddress address) {
        udpServer.removeListener(address);
    }

    public void sendUDPPacket(String message, InetAddress address, int port) {
        try {
            udpServer.sendPacket(message, address, port);
        } catch (IOException ignored) {}
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

}
