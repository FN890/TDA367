package services;

import java.net.Socket;

/**
 * TCPListener used for listening on TCP connections from TCPServer.
 */
public interface TCPListener {
    /**
     * Called once a new connection has been established.
     *
     * @param client The client socket.
     */
    void gotConnection(Socket client);
}
