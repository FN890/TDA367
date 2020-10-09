package com.backendboys.battlerace.model.gamemodel.opponent;

import com.badlogic.gdx.math.Vector2;

public class OpponentPlayer {

    private final String playerName;
    private Vector2 playerPosition;
    private float playerRotation;

    public OpponentPlayer(String playerName, Vector2 playerPosition, float playerRotation) {
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerRotation = playerRotation;
    }

    public void setVectorPosition(Vector2 position, float rotation) {
        this.playerPosition = position;
        this.playerRotation = rotation;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Vector2 getPlayerPosition() {
        return playerPosition;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }
}
