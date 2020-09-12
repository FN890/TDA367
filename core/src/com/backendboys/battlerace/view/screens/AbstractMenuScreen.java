package com.backendboys.battlerace.view.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractMenuScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Texture background;

    private AbstractMenuScreen() {
        batch = new SpriteBatch();
        background = new Texture("bg-100.jpg");
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(getCamera().combined);

        batch.begin();
        batch.draw(background, 0, 0, getViewport().getWorldWidth(), getViewport().getWorldHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
