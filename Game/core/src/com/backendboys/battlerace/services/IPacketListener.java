package com.backendboys.battlerace.services;

/**
 * PacketListener used for listening on UDP Packets.
 */
public interface IPacketListener {

    /**
     * Called once a packet has been received from the server.
     *
     * @param message The message received.
     */
    void gotPacket(String message);

    void UDPErrorOccurred(String message);
}
