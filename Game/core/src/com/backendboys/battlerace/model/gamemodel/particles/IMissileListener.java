package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;

public interface IMissileListener {

    /**
     * Called whenever a missile is shot.
     *
     * @param position Start position of the missile.
     * @param velocity Start velocity of the missile.
     * @param rotation Start rotation of the missile.
     */
    void onMissileShot(Vector2 position, Vector2 velocity, float rotation);

}
