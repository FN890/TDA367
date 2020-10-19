package controller;

import services.PacketListener;
import services.TCPListener;
import services.TCPServer;
import services.UDPServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

/**
 * ServerController controls UDP and TCP servers.
 * Also forwards packets.
 */
public class ServerController implements TCPListener {

    private static final int PORT = 26000;
    private static ServerController instance = null;

    private TCPServer tcpServer;
    private UDPServer udpServer;

    private final Map<Socket, ClientController> connections;
    private int currentClientID = 0;

    private ServerController() {
        connections = Collections.synchronizedMap(new HashMap<>());

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
        synchronized (connections) {
            for (Socket s : connections.keySet()) {
                if (s.equals(client)) {
                    connections.get(s).disconnect();
                }
            }
            ClientController newConnection = new ClientController(client, createClientID());
            connections.put(client, newConnection);
            new Thread(newConnection).start();
        }
    }

    private String createClientID() {
        currentClientID += 1;
        return String.valueOf(currentClientID);
    }

    /**
     * Removes a connection from the list of connections.
     * @param client The socket to remove.
     */
    public void removeConnection(Socket client) {
        synchronized (connections) {
            connections.remove(client);
        }
    }

    /**
     * Adds a PacketListener to the running UDPServer.
     * Used for listening to incoming packets from specific clients.
     *
     * @param clientID  Client identifier.
     * @param listener The PacketListener.
     */
    public synchronized void addPacketListener(String clientID, PacketListener listener) {
        udpServer.addListener(clientID, listener);
    }

    /**
     * Removes a PacketListener from the running UDPServer.
     *
     * @param clientID The client identifier to remove.
     */
    public synchronized void removePacketListener(String clientID) {
        udpServer.removeListener(clientID);
    }

    /**
     * Send a Packet from the currently running UDPServer.
     *
     * @param message The message to send.
     * @param address The address to send the message.
     * @param port    The port to send the message.
     */
    public synchronized void sendUDPPacket(String message, InetAddress address, int port) {
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
            instance = new ServerController();
        }
        return instance;
    }
}
