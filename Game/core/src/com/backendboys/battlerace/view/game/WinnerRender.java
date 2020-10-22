package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.controller.GameController;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinnerRender extends AbstractRender {

    private final BitmapFont bitmapFont;
    private final GameController gameController;

    public WinnerRender(OrthographicCamera camera, GameController gameController) {
        super(camera);
        bitmapFont = new BitmapFont();
        this.gameController = gameController;
    }

    public void render(SpriteBatch batch) {
        if (gameController.getWinnerName() != null) {
            if (!gameController.getWinnerName().isEmpty()) {
                batch.setProjectionMatrix(getProjectionMatrix());
                batch.begin();
                bitmapFont.draw(batch, "Winner: " + gameController.getWinnerName(), getXPosition(), getYPosition());
                batch.end();
            }
        }
    }

    private float getXPosition() {
        return getCameraPosition().x - getViewportWidth() / 2 + 50;
    }

    private float getYPosition() {
        return getCameraPosition().y + getViewportHeight() / 4;
    }

}
