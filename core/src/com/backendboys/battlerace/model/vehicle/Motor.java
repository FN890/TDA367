package com.backendboys.battlerace.model.vehicle;

class Motor {

    private final float topSpeed;
    private final float acceleration;
    private final float angularAcceleration;

    Motor(float topSpeed, float acceleration, float angularAcceleration) {
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.angularAcceleration = angularAcceleration;
    }

    float getTopSpeed() {
        return topSpeed;
    }

    float getAcceleration() {
        return acceleration;
    }

    float getAngularAcceleration() {
        return angularAcceleration;
    }

}
