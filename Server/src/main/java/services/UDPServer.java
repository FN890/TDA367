package services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * UDPServer class used for setting up a UDP Server.
 */
public class UDPServer implements Runnable {

    private final int port;
    private final DatagramSocket socket;

    private final Map<InetAddress, PacketListener> listeners = Collections.synchronizedMap(new HashMap<>());

    /**
     * Adds a PacketListener class associated with a client InetAddress
     * for listening on incoming packets from this address.
     *
     * @param address  The address on which will be associated with the PacketListener.
     * @param listener The PacketListener class.
     */
    public void addListener(InetAddress address, PacketListener listener) {
        listeners.put(address, listener);
    }

    /**
     * Removes a PacketListener with the InetAddress.
     *
     * @param address The InetAddress.
     */
    public void removeListener(InetAddress address) {
        listeners.remove(address);
    }

    /**
     * Creates an instance of the UDPServer, and setting up necessary parameters.
     *
     * @param port The port on which the server will run.
     * @throws SocketException If error occurs on creating a DatagramSocket.
     */
    public UDPServer(int port) throws SocketException {
        this.port = port;
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
                System.out.println("Packet received: " + message);

                sendToListener(request.getAddress(), message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a packet message to the listener specified by address.
     *
     * @param address The address associated with a PacketListener.
     * @param message The message.
     */
    private void sendToListener(InetAddress address, String message) {
        synchronized (listeners) {
            if (listeners.containsKey(address)) {
                listeners.get(address).gotPacket(message);
            }
        }
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

        DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, address, 25000);
        socket.send(packet);
    }

}
