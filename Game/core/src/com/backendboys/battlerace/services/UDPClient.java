package com.backendboys.battlerace.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * UDPClient, used to send packets to UDPServer.
 */
public class UDPClient implements Runnable {

    private final String hostname;
    private final int port;
    private DatagramSocket socket;

    private final List<IPacketListener> listeners = new ArrayList<>();

    public UDPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Adds a listener, which can be notified
     *
     * @param listener
     */
    public void addListener(IPacketListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener
     *
     * @param listener
     */
    public void removeListener(IPacketListener listener) {
        listeners.remove(listener);
    }

    /**
     * Running forever and get received packets
     */
    @Override
    public void run() {
        byte[] buffer = new byte[548];

        try {
            socket = new DatagramSocket();

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                notifyGotPacket(message);
            }

        } catch (IOException e) {
            notifyErrorOccurred(e.getMessage());
        }
    }

    /**
     * Sends UDP DatagramPacket to server
     *
     * @param message
     */
    public void sendPacket(String message) {
        try {
            byte[] byteMsg = message.getBytes();

            DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, InetAddress.getByName(hostname), port);
            socket.send(packet);

        } catch (Exception e) {
            notifyErrorOccurred(e.getMessage());
        }
    }

    /**
     * Closes the socket
     */
    public void close() {
        socket.close();
    }

    /**
     * @param msg Sends got packet msg to listeners
     */
    private void notifyGotPacket(String msg) {
        for (IPacketListener l : listeners) {
            l.gotPacket(msg);
        }
    }

    /**
     * @param msg Sends error occurred msg to listeners
     */
    private void notifyErrorOccurred(String msg) {
        for (IPacketListener l : listeners) {
            l.UDPErrorOccurred(msg);
        }
    }

}
