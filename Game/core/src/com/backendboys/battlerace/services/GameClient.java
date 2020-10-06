package com.backendboys.battlerace.services;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient implements Runnable, IPacketListener{

    private String hostname;
    private int port;
    private UDPClient udpClient;

    public GameClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try(Socket socket = new Socket(hostname, port)){

            udpClient = new UDPClient(port);

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    @Override
    public void gotPacket(String message) {

    }
}
