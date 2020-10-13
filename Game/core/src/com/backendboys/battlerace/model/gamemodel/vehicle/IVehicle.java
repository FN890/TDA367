package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

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

    void setAcceleration(float acceleration);

    float getAcceleration();

    World getWorld();
}
