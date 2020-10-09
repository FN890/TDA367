package com.backendboys.battlerace.services.opponent;

import com.badlogic.gdx.math.Vector2;

public class OpponentPlayer {

    private String playerName;
    private Vector2 playerPosition;
    private float playerRotation;

    public OpponentPlayer(String playerName, Vector2 playerPosition, float playerRotation) {
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerRotation = playerRotation;
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
