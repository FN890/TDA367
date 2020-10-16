package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.List;

public class MissileRender extends AbstractRender<List<IParticle>> {

    private final static int WIDTH = 40, HEIGHT = 20;

    private final Sprite sprite;

    public MissileRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);

        Texture texture = new Texture(Gdx.files.internal("missile.png"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setOrigin(WIDTH / 2f, HEIGHT / 2f);
    }

    @Override
    public void render(SpriteBatch batch, List<IParticle> object) {
        batch.begin();
        for (IParticle missile : object) {
            if (withinCamera(missile)) {
                sprite.setPosition(missile.getPosition().x - WIDTH / 2f, missile.getPosition().y - HEIGHT / 2f);
                sprite.setRotation(MathUtils.radiansToDegrees * (missile.getRotation()));
                batch.setProjectionMatrix(getCamera().combined);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    public void dispose() {
    }

    private boolean withinCamera(IParticle missile) {
        if (missile.getPosition().x > getCamera().position.x - getCamera().viewportWidth) {
            return missile.getPosition().x < getCamera().position.x + getCamera().viewportWidth;
        }
        return true;
    }
}
