package model;

import data.Vector2;

public class Player {

    private final String name;
    private Vector2 position = null;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public Vector2 getPosition() {
        return position;
    }
}
