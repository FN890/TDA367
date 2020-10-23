package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.powerups.IPowerUp;
import com.backendboys.battlerace.model.powerups.PowerUpType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Class used for rendering the power up that the player is currently holding.
 */
public class CurrentPowerUpRender extends AbstractRender {

    private final Sprite nitroSprite;
    private final Sprite missileSprite;
    private final Sprite noPowerUpSprite;

    private static final int SPRITE_SIZE = 50;
    private static final int CAMERA_OFFSET_X = 240;
    private static final int CAMERA_OFFSET_Y = 130;

    /**
     * Sets the images and sizes for the sprites.
     *
     * @param camera The camera the game uses
     */
    public CurrentPowerUpRender(OrthographicCamera camera) {
        super(camera);

        nitroSprite = new Sprite(new Texture("nitro.png"));
        nitroSprite.setSize(SPRITE_SIZE, SPRITE_SIZE);
        nitroSprite.setOriginCenter();

        missileSprite = new Sprite(new Texture("missile.png"));
        missileSprite.setSize(SPRITE_SIZE, SPRITE_SIZE);
        missileSprite.setOriginCenter();
        missileSprite.setRotation(45);

        noPowerUpSprite = new Sprite(new Texture("powerp.png"));
        noPowerUpSprite.setSize(SPRITE_SIZE, SPRITE_SIZE);
        noPowerUpSprite.setOriginCenter();
    }

    /**
     * Method that calls the rendering method, this method is called every step of the world.
     *
     * @param batch   Used to draw sprites in the world.
     * @param powerUp The power up currently held by the player.
     */
    public void render(SpriteBatch batch, IPowerUp powerUp) {
        renderPowerUp(batch, powerUp, getCameraPosition());
    }

    /**
     * Renders the images for the power up.
     * Checks what power up player is holding, if any and renders correct sprite for that power up.
     *
     * @param batch    Used to draw the sprites.
     * @param powerUp  The power up the player is currently holding and is to be rendered.
     * @param position The position of the camera, so sprites stay at same place in the camera view.
     */
    private void renderPowerUp(SpriteBatch batch, IPowerUp powerUp, Vector2 position) {
        batch.begin();
        batch.setProjectionMatrix(getProjectionMatrix());

        if (powerUp == null) {
            noPowerUpSprite.setPosition(position.x + CAMERA_OFFSET_X, position.y + CAMERA_OFFSET_Y);
            noPowerUpSprite.draw(batch);
        } else if (powerUp.getPowerUpType() == PowerUpType.NITRO) {
            nitroSprite.setPosition(position.x + CAMERA_OFFSET_X, position.y + CAMERA_OFFSET_Y);
            nitroSprite.draw(batch);
        } else if (powerUp.getPowerUpType() == PowerUpType.MISSILE) {
            missileSprite.setPosition(position.x + CAMERA_OFFSET_X, position.y + CAMERA_OFFSET_Y);
            missileSprite.draw(batch);
        }

        batch.end();
    }

    public void dispose() {
    }
}
