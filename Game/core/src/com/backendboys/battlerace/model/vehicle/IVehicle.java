package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Interface for accessing and handling Vehicle.
 */
public interface IVehicle {

    /**
     * Method for when car is accelerating.
     */
    void gas();

    /**
     * Method for when car is braking.
     */
    void brake();

    /**
     * Method for rotating the car clockwise.
     */
    void rotateRight();

    /**
     * Method for rotating the car counter-clockwise.
     */
    void rotateLeft();

    /**
     * Gets the position of the car in world.
     *
     * @return Position in the form of {@link Vector2}.
     */
    Vector2 getPosition();

    /**
     * Gets the rotation of the car.
     *
     * @return The rotation in the form of a float in radians.
     */
    float getRotation();

    /**
     * Sets the acceleration of the car.
     * Used for boosting it's acceleration when using {@link com.backendboys.battlerace.model.powerups.NitroPowerUp}.
     *
     * @param acceleration The new acceleration in the form of a float.
     */
    void setAcceleration(float acceleration);

    /**
     * Gets the acceleration of the car.
     *
     * @return The acceleration in the form of a float.
     */
    float getAcceleration();

    /**
     * Gets the world the car is currently in.
     *
     * @return The {@link World} in which the car is in.
     */
    World getWorld();

    /**
     * Gets the linear velocity of the car.
     *
     * @return The linear velocity in the form of a {@link Vector2}.
     */
    Vector2 getLinearVelocity();
}
