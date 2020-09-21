package com.backendboys.battlerace.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

abstract class AbstractScreen implements Screen {

    private Viewport viewport;
    private OrthographicCamera camera;

    protected AbstractScreen() {
        camera = new OrthographicCamera();
        viewport = new FillViewport(1280, 800, camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    protected OrthographicCamera getCamera() {
        return this.camera;
    }

    protected Viewport getViewport() {
        return this.viewport;
    }
}
