package services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * TCPServer class used for setting up a TCP Server.
 */
public class TCPServer implements Runnable {

    private final int port;
    private final ServerSocket serverSocket;

    private List<TCPListener> listeners = new ArrayList<>();

    /**
     * Adds a listener for listening to incoming connections.
     *
     * @param listener The TCPListener.
     */
    public void addListener(TCPListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener.
     *
     * @param listener The TCPListener to remove.
     */
    public void removeListener(TCPListener listener) {
        listeners.remove(listener);
    }

    /**
     * Creates an instance of TCPServer, which initializes necessary parameters.
     *
     * @param port The port on which the server will run.
     * @throws IOException If error occurs when creating the ServerSocket.
     */
    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    /**
     * This method runs whenever a new Thread is started with this Runnable.
     * Tells the Server to start listening for incoming connections.
     * Will create new Thread and ClientController on connection.
     */
    @Override
    public void run() {
        System.out.println("Server started listening on port: " + port);

        while (true) {
            try {
                Socket client = serverSocket.accept();

                System.out.println("Client connected to the Server: " + client.getInetAddress().getHostAddress());
                informListenersGotConnection(client);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void informListenersGotConnection(Socket client) {
        for (TCPListener l : listeners) {
            l.gotConnection(client);
        }
    }

}
