package com.backendboys.battlerace.model.opponent;

import com.badlogic.gdx.math.Vector2;

public class OpponentPlayer {

    private final String name;
    private Vector2 position;
    private float rotation;

    /**
     * Constructor for the class, sets the start values.
     *
     * @param name     Name of the opponent
     * @param position Position of the oppponent in the world.
     * @param rotation The rotation of the players vehicle in the world.
     */
    public OpponentPlayer(String name, Vector2 position, float rotation) {
        this.name = name;
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Sets the position and rotation of the opponent vehicle in the world.
     *
     * @param position Opponents vehicle position in world.
     * @param rotation Opponoents vehicle rotation in world.
     */
    public void setVectorPosition(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Getter for the name of the opponent.
     *
     * @return The name of the opponent the form of a {@link String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the position of the opponent in the world.
     *
     * @return The position of the opponent in the form a {@link Vector2}.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Getter for the rotation of the opponent in the world.
     *
     * @return The rotation of the opponent in form of a float in degrees from 0-360.
     */
    public float getRotation() {
        return rotation;
    }
}
