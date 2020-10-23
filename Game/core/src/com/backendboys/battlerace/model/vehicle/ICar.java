package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;

public interface ICar extends IVehicle {

    /**
     * Returns the position of the front wheel.
     *
     * @return the position.
     */
    Vector2 getFrontWheelPosition();

    /**
     * Returns the position of the rear wheel.
     *
     * @return the position.
     */
    Vector2 getRearWheelPosition();

    /**
     * Returns the rotation of the front wheel.
     *
     * @return the rotation.
     */
    float getFrontWheelRotation();

    /**
     * Returns the rotation of the rear wheel.
     *
     * @return the rotation.
     */
    float getRearWheelRotation();
}
