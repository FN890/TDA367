package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.services.IPacketListener;
import com.backendboys.battlerace.services.ITCPListener;
import com.backendboys.battlerace.services.TCPClient;
import com.backendboys.battlerace.services.UDPClient;

public class ServerController implements ITCPListener, IPacketListener {

    private final static String HOSTNAME = "167.172.34.88";
    private final static int PORT = 26000;

    private TCPClient tcpClient;
    private UDPClient udpClient;

    public ServerController() {
        udpClient = new UDPClient(PORT);
        udpClient.addListener(this);
        new Thread(udpClient).start();

        tcpClient = new TCPClient(HOSTNAME, PORT);
        tcpClient.addListener(this);
        new Thread(tcpClient).start();
    }


    @Override
    public void gotPacket(String message) {

    }

    @Override
    public void UDPErrorOccurred(String message) {

    }

    @Override
    public void gotMessage(String message) {

    }

    @Override
    public void lostConnection(String message) {

    }
}
