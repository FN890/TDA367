package com.backendboys.battlerace.model.opponent;

import com.badlogic.gdx.math.Vector2;

public class OpponentPlayer {

    private final String name;
    private Vector2 position;
    private float rotation;

    public OpponentPlayer(String name, Vector2 position, float rotation) {
        this.name = name;
        this.position = position;
        this.rotation = rotation;
    }

    public void setVectorPosition(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
