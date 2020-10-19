package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;

public interface IMissileListener {

    void onMissileShot(Vector2 position, Vector2 velocity, float rotation);

}
