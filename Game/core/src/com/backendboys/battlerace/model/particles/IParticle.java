package com.backendboys.battlerace.model.particles;


import com.badlogic.gdx.math.Vector2;

/**
 * an interface used to identify particles and missiles
 * also used when getting the info for rendering
 */
public interface IParticle {

    /**
     * Returns the position of the IParticle.
     *
     * @return the position.
     */
    Vector2 getPosition();

    /**
     * Returns the rotation of the IParticle.
     *
     * @return the rotation.
     */
    float getRotation();
}
