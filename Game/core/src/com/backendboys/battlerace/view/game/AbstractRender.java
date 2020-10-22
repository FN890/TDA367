package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractRender{

    private final OrthographicCamera camera;

    private Vector2 cameraPos;

    protected AbstractRender(OrthographicCamera camera) {
        this.camera = camera;
    }

    protected float getViewportWidth() {
        return camera.viewportWidth;
    }

    protected float getViewportHeight() {
        return camera.viewportHeight;
    }

    protected Matrix4 getProjectionMatrix() {
        return camera.combined;
    }

    protected Vector2 getCameraPosition() {
        if (cameraPos == null) {
            cameraPos = new Vector2(camera.position.x, camera.position.y);
        }
        cameraPos.set(camera.position.x, camera.position.y);
        return cameraPos;
    }
}
