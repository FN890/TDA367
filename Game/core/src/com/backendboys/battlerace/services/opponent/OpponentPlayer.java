package com.backendboys.battlerace.services.opponent;

import com.badlogic.gdx.math.Vector2;

public class OpponentPlayer {

    private String playerName;
    private Vector2 playerPosition;

    public OpponentPlayer(String playerName, Vector2 playerPosition){
        this.playerName = playerName;
        this.playerPosition = playerPosition;
    }
}
