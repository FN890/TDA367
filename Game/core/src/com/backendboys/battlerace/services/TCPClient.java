package com.backendboys.battlerace.services;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPClient implements Runnable {

    private final String hostname;
    private final int port;

    private PrintWriter printWriter;

    private final List<ITCPListener> listeners = new ArrayList<>();

    public TCPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void addListener(ITCPListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITCPListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostname, port)) {

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            notifyConnected();

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                notifyGotMessage(line);
            }

        } catch (Exception e) {
            notifyLostConnection(e.getMessage());
        }
    }

    public void sendMessage(String message) {
        printWriter.println(message);
    }

    private void notifyLostConnection(String msg) {
        for (ITCPListener l : listeners) {
            l.lostConnection(msg);
        }
    }

    private void notifyGotMessage(String msg) {
        for (ITCPListener l : listeners) {
            l.gotMessage(msg);
        }
    }

    private void notifyConnected() {
        for (ITCPListener l : listeners) {
            l.onConnection();
        }
    }
}
