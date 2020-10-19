package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * class that is used to render particles
 */
public class ExplosionParticleRender extends AbstractRender<List<IParticle>> {

    private final static int WIDTH = 5, HEIGHT = 5;

    private final Sprite sprite;

    /**
     * creates the renderer
     *
     * @param orthographicCamera The camera in which we view what we render
     */
    public ExplosionParticleRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);

        Texture texture = new Texture(Gdx.files.internal("fragment.png"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setOrigin(WIDTH / 2f, HEIGHT / 2f);
    }

    /**
     * The method that needs to be called everytime you render the game
     *
     * @param batch  batch used to draw on the screen
     * @param object the particle object to be rendered
     */
    @Override
    public void render(SpriteBatch batch, List<IParticle> object) {
        batch.begin();
        for (IParticle particle : object) {
            if (withinCamera(particle)) {
                sprite.setPosition(particle.getPosition().x - 2.5f, particle.getPosition().y - 2.5f);
                batch.setProjectionMatrix(getCamera().combined);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    private boolean withinCamera(IParticle particle) {
        if (particle.getPosition().x > getCamera().position.x - getCamera().viewportWidth) {
            return particle.getPosition().x < getCamera().position.x + getCamera().viewportWidth;
        }
        return true;
    }

    public void dispose() {

    }

}
