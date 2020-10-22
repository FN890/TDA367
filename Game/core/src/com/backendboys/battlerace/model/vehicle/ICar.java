package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;

public interface ICar extends IVehicle {

    Vector2 getFrontWheelPosition();

    Vector2 getRearWheelPosition();

    float getFrontWheelRotation();

    float getRearWheelRotation();
}
