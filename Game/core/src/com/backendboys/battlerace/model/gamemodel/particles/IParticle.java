package com.backendboys.battlerace.model.gamemodel.particles;


import com.badlogic.gdx.math.Vector2;

/**
 * an interface used to identify particles and missiles
 * also used when getting the info for rendering
 */
public interface IParticle {

    Vector2 getPosition();

    float getRotation();
}
