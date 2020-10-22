package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.vehicle.ICar;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;



public class IdRender extends AbstractRender<Object> {


    private static final int CAMERA_OFFSET_X = 240;
    private static final int CAMERA_OFFSET_Y = 130;

    private final Label lblGameId;
    private final GameController gameController;

    public IdRender(OrthographicCamera orthographicCamera, GameController gameController) {
        super(orthographicCamera);
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        this.gameController = gameController;
        lblGameId = new Label("Game Id: 1400", uiSkin);
        lblGameId.setFontScale(0.5f);
    }

    @Override
    public void render(SpriteBatch batch, Object object) {
        batch.begin();
        batch.setProjectionMatrix(getProjectionMatrix());

        if(gameController.getServerController() != null) {
            if (gameController.getServerController().isConnected()) {
                lblGameId.setPosition((getCameraPosition().x - getViewportWidth()/2.01f), getCameraPosition().y + getViewportHeight()/2.25f);
                lblGameId.setText("Game id: " + gameController.getServerController().getGameId());
                lblGameId.draw(batch, 1);
            }
        }

        batch.end();
    }

    public void dispose() {
    }

}
