package model;

import data.Address;
import data.Vector2;

import java.net.InetAddress;

/**
 * Player class used once in game. Holds the in-game name, position, and addresses.
 */
public class Player {

    private final String name;
    private Vector2 position = null;
    private float rotation;

    private Address UDPAddress = null;

    /**
     * Initializes a Player.
     *
     * @param name    The player name.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Setup Player's UDP address and port for sending them packets.
     *
     * @param address The InetAddress.
     * @param port The port.
     */
    public void setUDPAddress(InetAddress address, int port) {
        UDPAddress = new Address(address, port);
    }

    /**
     * Returns wether the Player's UDP Address is set or not.
     * @return True if the Player's UDP Address is set.
     */
    public boolean hasUDPAddress() {
        return (UDPAddress != null);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return UDPAddress;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
