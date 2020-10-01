package model;

import data.Vector2;

import java.net.InetAddress;

/**
 * Player class used once in game. Holds the in-game name, position, and addresses.
 */
public class Player {

    private final String name;
    private final InetAddress address;
    private final int port;

    private Vector2 position = null;
    private float rotation;

    /**
     * Initializes a Player.
     * @param name The player name.
     * @param address The players InetAddress.
     * @param port The players port.
     */
    public Player(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
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

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
