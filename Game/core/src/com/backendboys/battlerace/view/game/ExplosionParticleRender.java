package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ExplosionParticleRender {

    private final OrthographicCamera orthographicCamera;

    private final SpriteBatch batch;
    private final Sprite sprite;

    private final static int WIDTH = 5, HEIGHT = 5;

    public ExplosionParticleRender(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;

        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("fragment.png"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setOrigin(WIDTH / 2f, HEIGHT / 2f);
    }
}
