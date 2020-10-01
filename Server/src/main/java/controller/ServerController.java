package controller;

import services.PacketListener;
import services.TCPListener;
import services.TCPServer;
import services.UDPServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ServerController controls UDP and TCP servers.
 * Also forwards packets.
 */
public class ServerController implements TCPListener {

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
            server.addListener(this);
            server.listen();
            return server;
        } catch (IOException ignored) {}

        System.out.println("ERROR: Could not create TCP Server.");
        return null;
    }

    @Override
    public void gotConnection(Socket client) {
        new Thread(new ClientController(client)).start();
    }

    /**
     * Adds a PacketListener to the running UDPServer.
     * Used for listening to incoming packets from specific clients.
     * @param address Client InetAddress.
     * @param listener The PacketListener.
     */
    public void addPacketListener(InetAddress address, PacketListener listener) {
        udpServer.addListener(address, listener);
    }

    /**
     * Removes a PacketListener from the running UDPServer.
     * @param address The client InetAddress to remove.
     */
    public void removePacketListener(InetAddress address) {
        udpServer.removeListener(address);
    }

    /**
     * Send a Packet from the currently running UDPServer.
     * @param message The message to send.
     * @param address The address to send the message.
     * @param port The port to send the message.
     */
    public void sendUDPPacket(String message, InetAddress address, int port) {
        try {
            udpServer.sendPacket(message, address, port);
        } catch (IOException ignored) {}
    }

    /**
     * @return The instance of ServerController.
     */
    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }
}
