package com.backendboys.battlerace.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu extends AbstractScreen implements Screen {

    private SpriteBatch batch;
    private Stage stage;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //batch.setProjectionMatrix(camera.combined);
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
}
