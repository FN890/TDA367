package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Class that handles rendering the power-ups.
 */
public class PowerUpsRender {

    private final OrthographicCamera orthographicCamera;
    private final ArrayList<AbstractPowerUp> powerUps;
    private final SpriteBatch batch;
    private final Sprite sprite;

    private final static int WIDTH = 10, HEIGHT = 10;

    public PowerUpsRender(OrthographicCamera orthographicCamera, ArrayList<AbstractPowerUp> powerUps) {
        this.orthographicCamera = orthographicCamera;
        this.powerUps = powerUps;
        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("powerp.jpg"));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
    }

    /**
     * Render the power-ups that are within view. Should be called every render.
     */
    public void renderPowerUps() {
        render();
    }

    private void render() {
        for (AbstractPowerUp powerUp : powerUps) {
            if (withinCamera(powerUp)) {
                batch.begin();
                batch.setProjectionMatrix(orthographicCamera.combined);
                sprite.setPosition(powerUp.getBody().getPosition().x - 5, powerUp.getBody().getPosition().y - 5);
                sprite.draw(batch);
                batch.end();
            }
        }
    }

    private boolean withinCamera(AbstractPowerUp powerUp) {
        if (powerUp.getBody().getPosition().x > orthographicCamera.position.x - orthographicCamera.viewportWidth) {
            return powerUp.getBody().getPosition().x < orthographicCamera.position.x + orthographicCamera.viewportWidth;
        }
        return false;
    }

    public void dispose() {
        batch.dispose();
    }

}
