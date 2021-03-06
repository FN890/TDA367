package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.opponent.OpponentPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.List;

public class OpponentRender extends AbstractRender {

    private static final int VEHCILE_WIDTH = 60;
    private static final int VEHCILE_HEIGHT = 25;

    private final Sprite spritePlayerVehicle;
    private final Label lblPlayerName;

    public OpponentRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);
        spritePlayerVehicle = new Sprite(new Texture("BlueCar.png"));
        spritePlayerVehicle.setSize(VEHCILE_WIDTH, VEHCILE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        lblPlayerName = new Label("Opponent", uiSkin);
    }

    public void render(SpriteBatch batch, List<OpponentPlayer> object) {
        batch.begin();
        for (OpponentPlayer o : object) {
            renderOpponent(batch, o);
            renderOpponentName(batch, o);
        }
        batch.end();
    }

    private void renderOpponent(SpriteBatch batch, OpponentPlayer opponent) {
        spritePlayerVehicle.setPosition(opponent.getPosition().x - VEHCILE_WIDTH / 2f, opponent.getPosition().y - VEHCILE_HEIGHT / 2f);
        spritePlayerVehicle.setRotation((float) Math.toDegrees(opponent.getRotation()));

        spritePlayerVehicle.draw(batch);
    }

    private void renderOpponentName(SpriteBatch batch, OpponentPlayer opponent) {
        lblPlayerName.setText(opponent.getName());
        lblPlayerName.setPosition(opponent.getPosition().x - VEHCILE_WIDTH / 2f, opponent.getPosition().y + 25);

        lblPlayerName.draw(batch, 1);
    }


    public void dispose() {
    }

}
