package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.model.IModelListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen implements IModelListener {

    SpriteBatch batch;
    Texture img;


    public void init() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }


    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public void update() {

    }
}
