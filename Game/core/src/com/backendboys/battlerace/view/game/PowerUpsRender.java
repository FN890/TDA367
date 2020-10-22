package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.powerups.IPowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * Class that handles rendering the power-ups.
 */
public class PowerUpsRender extends AbstractRender {

    private final List<IPowerUp> powerUps;
    private final Sprite sprite;

    private final static int WIDTH = 10, HEIGHT = 10;

    public PowerUpsRender(OrthographicCamera orthographicCamera, List<IPowerUp> powerUps) {
        super(orthographicCamera);
        this.powerUps = powerUps;

        Texture texture = new Texture(Gdx.files.internal("powerp.png"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        for (IPowerUp powerUp : powerUps) {
            if (withinCamera(powerUp)) {
                batch.setProjectionMatrix(getProjectionMatrix());
                sprite.setPosition(powerUp.getPosition().x - 5, powerUp.getPosition().y - 5);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    private boolean withinCamera(IPowerUp powerUp) {
        if (powerUp.getPosition().x > getCameraPosition().x - getViewportWidth()) {
            return powerUp.getPosition().x < getCameraPosition().x + getViewportWidth();
        }
        return false;
    }

    public void dispose() {
    }

}
