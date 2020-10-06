package com.backendboys.battlerace.services;

/**
 * PacketListener used for listening on UDP Packets.
 */
public interface PacketListener {

    /**
     * Called once a packet has been received, and its from the right InetAddress.
     *
     * @param message The message received.
     */
    void gotPacket(String message);
}
