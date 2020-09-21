package com.backendboys.battlerace.model.gamemodel.vehicle;

class Wheels {

    private final float radius;
    private final float friction;
    private final float density;
    private final float restitution;

    Wheels(float radius, float friction, float density, float restitution) {
        this.radius = radius;
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;
    }

    float getRadius() {
        return radius;
    }

    float getFriction() {
        return friction;
    }

    float getDensity() {
        return density;
    }

    float getRestitution() {
        return restitution;
    }

}
