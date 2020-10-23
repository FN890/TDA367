package com.backendboys.battlerace.services;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * TCPClient, used to connect to TCPServer on server.
 */
public class TCPClient implements Runnable {

    private final String hostname;
    private final int port;

    private Socket socket;

    private PrintWriter printWriter;

    private final List<ITCPListener> listeners = new ArrayList<>();

    public TCPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Adds a listener, which can be notified
     * @param listener
     */
    public void addListener(ITCPListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener
     * @param listener
     */
    public void removeListener(ITCPListener listener) {
        listeners.remove(listener);
    }

    /**
     * Running forever and notifies listener when we get new message
     */
    @Override
    public void run() {
        try {
            socket = new Socket(hostname, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            notifyConnected();

            String line;

            while ((line = reader.readLine()) != null) {

                notifyGotMessage(line);
            }

        } catch (Exception e) {
            notifyLostConnection(e.getMessage());
        }
    }

    /**
     *
     * @param message Send message to server
     */
    public void sendMessage(String message) {
        printWriter.println(message);
    }

    /**
     * Closes the socket
     */
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    /**
     *
     * @param msg Sends lost connection msg to listener
     */
    private void notifyLostConnection(String msg) {
        for (ITCPListener l : listeners) {
            l.lostConnection(msg);
        }
    }

    /**
     *
     * @param msg Sends got msg to listener
     */
    private void notifyGotMessage(String msg) {
        for (ITCPListener l : listeners) {
            l.gotMessage(msg);
        }
    }

    /**
     *
     * Sends connected msg to listener
     */
    private void notifyConnected() {
        for (ITCPListener l : listeners) {
            l.onConnection();
        }
    }
}
