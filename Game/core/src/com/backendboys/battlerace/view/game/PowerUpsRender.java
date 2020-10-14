package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * Class that handles rendering the power-ups.
 */
public class PowerUpsRender extends AbstractRender<Object> {

    private final List<AbstractPowerUp> powerUps;
    private final Sprite sprite;

    private final static int WIDTH = 10, HEIGHT = 10;

    public PowerUpsRender(OrthographicCamera orthographicCamera, List<AbstractPowerUp> powerUps) {
        super(orthographicCamera);
        this.powerUps = powerUps;

        Texture texture = new Texture(Gdx.files.internal("powerp.jpg"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch, Object object) {
        render(batch);
    }

    private void render(SpriteBatch batch) {
        batch.begin();
        for (AbstractPowerUp powerUp : powerUps) {
            if (withinCamera(powerUp)) {
                batch.setProjectionMatrix(getCamera().combined);
                sprite.setPosition(powerUp.getBody().getPosition().x - 5, powerUp.getBody().getPosition().y - 5);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    private boolean withinCamera(AbstractPowerUp powerUp) {
        if (powerUp.getBody().getPosition().x > getCamera().position.x - getCamera().viewportWidth) {
            return powerUp.getBody().getPosition().x < getCamera().position.x + getCamera().viewportWidth;
        }
        return false;
    }

    public void dispose() {
    }

}
