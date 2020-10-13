package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

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

    public void render(ArrayList<IParticle> particles) {
        batch.begin();
        for (IParticle particle : particles) {
            if (withinCamera(particle)) {
                sprite.setPosition(particle.getPosition().x - 2.5f, particle.getPosition().y - 2.5f);
                batch.setProjectionMatrix(orthographicCamera.combined);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    private boolean withinCamera(IParticle particle) {
        if (particle.getPosition().x > orthographicCamera.position.x - orthographicCamera.viewportWidth) {
            return particle.getPosition().x < orthographicCamera.position.x + orthographicCamera.viewportWidth;
        }
        return true;
    }

    public void dispose() {
        batch.dispose();
    }

}
