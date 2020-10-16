package services;

import data.Address;

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

    private final Map<Address, PacketListener> listeners = Collections.synchronizedMap(new HashMap<>());

    /**
     * Adds a PacketListener class associated with a client InetAddress
     * for listening on incoming packets from this address.
     *
     * @param address  The Address on which will be associated with the PacketListener.
     * @param listener The PacketListener class.
     */
    public void addListener(Address address, PacketListener listener) {
        listeners.put(address, listener);
    }

    /**
     * Removes a PacketListener with the InetAddress.
     *
     * @param address The Address.
     */
    public void removeListener(Address address) {
        listeners.remove(address);
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
     * Sends a packet message to the listener specified by address.
     *
     * @param address The address associated with a PacketListener.
     * @param message The message.
     */
    private void sendToListener(InetAddress address, int port, String message) {
        Address addr = new Address(address, port);
        synchronized (listeners) {
            for (Address a : listeners.keySet()) {
                if (a.equals(addr)) {
                    listeners.get(a).gotPacket(address, port, message);
                }
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
        System.out.println("Sending packet to: " + address);
        System.out.println("Port: " + port);
        System.out.println("Message: " + message);
        DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, address, port);
        socket.send(packet);
    }

}
