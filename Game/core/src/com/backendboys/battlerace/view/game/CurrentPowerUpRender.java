package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.powerups.IPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class CurrentPowerUpRender extends AbstractRender<IPowerUp> {

    private final Sprite nitroSprite;
    private final Sprite missileSprite;
    private final Sprite noPowerUpSprite;

    private static final int SPRITE_SIZE = 50;

    public CurrentPowerUpRender(OrthographicCamera camera) {
        super(camera);

        nitroSprite = new Sprite(new Texture("newredcar.png"));
        nitroSprite.setSize(150, 150);
        nitroSprite.setOriginCenter();

        missileSprite = new Sprite(new Texture("wheel.png"));
        missileSprite.setSize(150, 150);
        missileSprite.setOriginCenter();

        noPowerUpSprite = new Sprite(new Texture("badlogic.jpg"));
        noPowerUpSprite.setSize(SPRITE_SIZE, SPRITE_SIZE);
        noPowerUpSprite.setOriginCenter();
    }

    @Override
    public void render(SpriteBatch batch, IPowerUp powerUp) {
        renderPowerUp(batch, powerUp, getCamera().position);
    }

    private void renderPowerUp(SpriteBatch batch, IPowerUp powerUp, Vector3 position) {
        batch.begin();
        batch.setProjectionMatrix(getCamera().combined);

        if (powerUp == null) {
            noPowerUpSprite.setPosition(position.x + getCamera().viewportWidth / 4, position.y + getCamera().viewportHeight / 4);
            noPowerUpSprite.draw(batch);
        }
        else if (powerUp instanceof NitroPowerUp) {
            nitroSprite.setPosition(position.x + 150, position.y + 150);
            nitroSprite.draw(batch);
        }
        else if (powerUp instanceof MissilePowerUp) {
            missileSprite.setPosition(position.x + 150, position.y+ 150);
            missileSprite.draw(batch);
        }

        batch.end();
    }

    public void dispose() {
    }
}
