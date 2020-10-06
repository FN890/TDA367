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

    private TCPServer tcpServer;
    private UDPServer udpServer;

    private ServerController() {
        try {
            udpServer = new UDPServer(PORT);
            new Thread(udpServer).start();

            tcpServer = new TCPServer(PORT);
            tcpServer.addListener(this);
            new Thread(tcpServer).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void gotConnection(Socket client) {
        new Thread(new ClientController(client)).start();
    }

    /**
     * Adds a PacketListener to the running UDPServer.
     * Used for listening to incoming packets from specific clients.
     *
     * @param address  Client InetAddress.
     * @param listener The PacketListener.
     */
    public void addPacketListener(InetAddress address, PacketListener listener) {
        udpServer.addListener(address, listener);
    }

    /**
     * Removes a PacketListener from the running UDPServer.
     *
     * @param address The client InetAddress to remove.
     */
    public void removePacketListener(InetAddress address) {
        udpServer.removeListener(address);
    }

    /**
     * Send a Packet from the currently running UDPServer.
     *
     * @param message The message to send.
     * @param address The address to send the message.
     * @param port    The port to send the message.
     */
    public void sendUDPPacket(String message, InetAddress address, int port) {
        try {
            udpServer.sendPacket(message, address, port);
        } catch (IOException ignored) {
        }
    }

    /**
     * @return The instance of ServerController.
     */
    public synchronized static ServerController getInstance() {
        if (instance == null) {
            System.out.println("Creating ServerController...");
            instance = new ServerController();
        }
        return instance;
    }
}
