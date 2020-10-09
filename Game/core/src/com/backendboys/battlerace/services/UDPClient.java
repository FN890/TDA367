package com.backendboys.battlerace.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient implements Runnable {

    private final DatagramSocket socket;
    private final int port;

    public UDPClient(int port) throws SocketException {
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[548];

        try {
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Packet received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
