package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class OpponentRender extends AbstractRender<List<OpponentPlayer>> {

    private static final int VEHCILE_WIDTH = 60;
    private static final int VEHCILE_HEIGHT = 25;

    private final Sprite spritePlayerVehicle;

    public OpponentRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);
        spritePlayerVehicle = new Sprite(new Texture("BlueCar.png"));
        spritePlayerVehicle.setSize(VEHCILE_WIDTH, VEHCILE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();
    }

    @Override
    public void render(SpriteBatch batch, List<OpponentPlayer> object) {
        batch.begin();
        for (OpponentPlayer o : object) {
            renderOpponent(batch, o);
        }
        batch.end();
    }

    private void renderOpponent(SpriteBatch batch, OpponentPlayer opponent) {
        spritePlayerVehicle.setPosition(opponent.getPlayerPosition().x - VEHCILE_WIDTH / 2f, opponent.getPlayerPosition().y - VEHCILE_HEIGHT / 2f);
        spritePlayerVehicle.setRotation((float) Math.toDegrees(opponent.getPlayerRotation()));

        spritePlayerVehicle.draw(batch);
    }


    public void dispose() {
    }

}
