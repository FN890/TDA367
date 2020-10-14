package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractRender<T> {

    private final OrthographicCamera camera;

    protected AbstractRender(OrthographicCamera camera) {
        this.camera = camera;
    }

    public abstract void render(SpriteBatch batch, T object);

    protected OrthographicCamera getCamera() {
        return camera;
    }
}
