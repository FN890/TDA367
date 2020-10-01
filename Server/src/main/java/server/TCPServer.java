package server;

import controller.ClientController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * TCPServer class used for setting up a TCP Server.
 */
public class TCPServer {

    private final int port;
    private final ServerSocket serverSocket;

    private List<TCPListener> listeners = new ArrayList<>();

    /**
     * Adds a listener for listening to incoming connections.
     * @param listener The TCPListener.
     */
    public void addListener(TCPListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener.
     * @param listener The TCPListener to remove.
     */
    public void removeListener(TCPListener listener) {
        listeners.remove(listener);
    }

    /**
     * Creates an instance of TCPServer, which initializes necessary parameters.
     * @param port The port on which the server will run.
     * @throws IOException If error occurs when creating the ServerSocket.
     */
    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    /**
     * Tells the Server to start listening for incoming connections.
     * Will create new Thread and ClientController on connection.
     * @throws IOException If error occurs when accepting connections.
     */
    public void listen() throws IOException {

        System.out.println("Server started listening on port: " + port);

        while (true) {
            Socket client = serverSocket.accept();

            System.out.println("Client connected to the Server: " + client.getInetAddress().getHostAddress());
            informListenersGotConnection(client);
        }

    }

    private void informListenersGotConnection(Socket client) {
        for (TCPListener l : listeners) {
            l.gotConnection(client);
        }
    }

}
