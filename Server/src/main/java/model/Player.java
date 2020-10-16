package model;

import data.Address;
import data.Vector2;

import java.net.InetAddress;

/**
 * Player class used once in game. Holds the in-game name, position, and addresses.
 */
public class Player {

    private String name;
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

    public void setUDPAddress(InetAddress address, int port) {
        UDPAddress = new Address(address, port);
    }

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
