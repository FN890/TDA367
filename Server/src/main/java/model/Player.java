package model;

import data.Vector2;

import java.net.InetAddress;

/**
 * Player class used once in game. Holds the in-game name, position, and addresses.
 */
public class Player {

    private String name;

    private InetAddress UDPAddress;
    private int UDPPort;

    private Vector2 position = null;
    private float rotation;

    /**
     * Initializes a Player.
     *
     * @param name    The player name.
     */
    public Player(String name) {
        this.name = name;
    }

    public void setUDPAddress(InetAddress address, int port) {
        this.UDPAddress = address;
        this.UDPPort = port;
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

    public InetAddress getAddress() {
        return UDPAddress;
    }

    public int getPort() {
        return UDPPort;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
