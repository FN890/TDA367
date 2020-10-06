package com.backendboys.battlerace.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient implements Runnable{

    private String hostname;
    private int port;

    private PrintWriter writer;
    private BufferedReader reader;

    public GameClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try{
            Socket socket = new Socket(hostname, port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
