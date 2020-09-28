package model;

import data.Vector2;

import java.net.InetAddress;

public class Player {

    private final String name;
    private final InetAddress address;

    private Vector2 position = null;

    public Player(String name, InetAddress address) {
        this.name = name;
        this.address = address;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public Vector2 getPosition() {
        return position;
    }
}
