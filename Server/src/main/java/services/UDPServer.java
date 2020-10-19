package services;

import data.Address;
import services.protocol.ICommand;
import services.protocol.ProtocolException;
import services.protocol.ServerProtocolFactory;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * UDPServer class used for setting up a UDP Server.
 */
public class UDPServer implements Runnable {

    private final DatagramSocket socket;

    private final Map<String, PacketListener> listeners = Collections.synchronizedMap(new HashMap<>());

    /**
     * Adds a PacketListener class associated with a client InetAddress
     * for listening on incoming packets from this address.
     *
     * @param clientID  The client ID on which will be associated with the PacketListener.
     * @param listener The PacketListener class.
     */
    public void addListener(String clientID, PacketListener listener) {
        listeners.put(clientID, listener);
    }

    /**
     * Removes a PacketListener with the InetAddress.
     *
     * @param clientID The Client ID.
     */
    public void removeListener(String clientID) {
        listeners.remove(clientID);
    }

    /**
     * Creates an instance of the UDPServer, and setting up necessary parameters.
     *
     * @param port The port on which the server will run.
     * @throws SocketException If error occurs on creating a DatagramSocket.
     */
    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {

        try {
            while (true) {
                byte[] buffer = new byte[548];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                sendToListener(request.getAddress(), request.getPort(), message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a packet message to the listener specified by client ID.
     * The client ID is specified before the command, it's specified by id-(command...)
     *
     * @param address The address associated with the packet.
     * @param port The port associated with the packet.
     * @param message The message.
     */
    private void sendToListener(InetAddress address, int port, String message) {
        try {
            int index = message.indexOf('-');
            String clientID = message.substring(0, index);
            String command = message.substring(index + 1);

            synchronized (listeners) {
                for (String id : listeners.keySet()) {
                    if (id.equals(clientID)) {
                        listeners.get(id).gotPacket(address, port, command);
                    }
                }
            }
        } catch (Exception ignored) {}

    }

    /**
     * Send a DatagramPacket containing a message to the specified address and port.
     *
     * @param message The message to send.
     * @param address The receiver address.
     * @param port    The receiver port.
     * @throws IOException If the DatagramPacket fails to send.
     */
    public void sendPacket(String message, InetAddress address, int port) throws IOException {
        byte[] byteMsg = message.getBytes();

        DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, address, port);
        socket.send(packet);
    }

}
