package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class MissileRender {

    private final OrthographicCamera orthographicCamera;

    private final SpriteBatch batch;
    private final Sprite sprite;

    private final static int WIDTH = 30, HEIGHT = 10;

    public MissileRender(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;

        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("missile.png"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setOrigin(WIDTH / 2f, HEIGHT / 2f);
    }

    public void render(ArrayList<IParticle> missiles) {
        batch.begin();
        for (IParticle missile : missiles) {
            if (withinCamera(missile)) {

                sprite.setPosition(missile.getPosition().x - 10, missile.getPosition().y - 6);
                sprite.setRotation(MathUtils.radiansToDegrees * (missile.getRotation()));
                batch.setProjectionMatrix(orthographicCamera.combined);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }

    private boolean withinCamera(IParticle missile) {
        if (missile.getPosition().x > orthographicCamera.position.x - orthographicCamera.viewportWidth) {
            return missile.getPosition().x < orthographicCamera.position.x + orthographicCamera.viewportWidth;
        }
        return true;
    }
}
