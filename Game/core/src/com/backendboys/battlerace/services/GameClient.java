package com.backendboys.battlerace.services;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient implements Runnable, IPacketListener{

    private String hostname;
    private int port;
    private UDPClient udpClient;

    private PrintWriter printWriter;

    public GameClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try(Socket socket = new Socket(hostname, port)){

            System.out.println("Connected to server");

            //Thread udpThread = new Thread(new UDPClient(port));
            //udpThread.start();

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            sendCommand();

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void gotPacket(String message) {

    }

    private void sendCommand(){
        System.out.println("Sending create:simon to server");
        printWriter.println("create:simon");
    }
}
