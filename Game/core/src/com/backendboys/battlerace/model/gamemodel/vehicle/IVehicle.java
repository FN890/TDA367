package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for accessing and handling Vehicle.
 */
public interface IVehicle {

    void gas();

    void brake();

    void rotateRight();

    void rotateLeft();

    Vector2 getPosition();

    float getRotation();

}
