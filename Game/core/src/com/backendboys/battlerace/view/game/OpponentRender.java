package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.vehicle.ICar;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class OpponentRender {

    private final SpriteBatch batch;
    private final Sprite spritePlayerVehicle;
    private final OrthographicCamera camera;

    private static final int VEHCILE_WIDTH = 60;
    private static final int VEHCILE_HEIGHT = 25;

    public OpponentRender(OrthographicCamera camera) {
        this.camera = camera;
        batch = new SpriteBatch();
        spritePlayerVehicle = new Sprite(new Texture("BlueCar.png"));
        spritePlayerVehicle.setSize(VEHCILE_WIDTH, VEHCILE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();
    }

    public void renderOpponents(List<OpponentPlayer> opponents) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for (OpponentPlayer o : opponents) {
            renderOpponent(o);
        }
        batch.end();
    }

    private void renderOpponent(OpponentPlayer opponent) {
        spritePlayerVehicle.setPosition(opponent.getPlayerPosition().x - VEHCILE_WIDTH / 2f, opponent.getPlayerPosition().y - VEHCILE_HEIGHT / 2f);
        spritePlayerVehicle.setRotation((float) Math.toDegrees(opponent.getPlayerRotation()));

        spritePlayerVehicle.draw(batch);
    }

    /**
     * Disposes the spriteBatch
     */
    public void dispose() {
        batch.dispose();
    }

}
