package com.backendboys.battlerace.services;

/**
 * ITCPListener used for listening on TCP server connection.
 */
public interface ITCPListener {

    /**
     * Called whenever a message has been received from the server.
     *
     * @param message The message received.
     */
    void gotMessage(String message);

    /**
     * Called when connection has been lost.
     *
     * @param message The error message.
     */
    void lostConnection(String message);

    /**
     * Called when a new connection has been established.
     */
    void onConnection();
}
