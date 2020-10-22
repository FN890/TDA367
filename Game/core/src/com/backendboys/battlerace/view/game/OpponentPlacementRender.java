package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.opponent.OpponentPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.List;

public class OpponentPlacementRender extends AbstractRender {

    private static final int VEHCILE_WIDTH_ICON = 30;
    private static final int VEHCILE_HEIGHT_ICON = 15;

    private final Sprite spriteOpponentVehicle;
    private final Label lblPlayerName;

    private final float sizeOfWorld;

    public OpponentPlacementRender(OrthographicCamera orthographicCamera, ArrayList<Vector2> groundVertices) {
        super(orthographicCamera);

        spriteOpponentVehicle = new Sprite(new Texture("BlueCar.png"));
        spriteOpponentVehicle.setSize(VEHCILE_WIDTH_ICON, VEHCILE_HEIGHT_ICON);
        spriteOpponentVehicle.setOriginCenter();

        sizeOfWorld = groundVertices.size();

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        lblPlayerName = new Label("Opponent", uiSkin);
        lblPlayerName.setFontScale(0.5f);
    }

    public void render(SpriteBatch batch, List<OpponentPlayer> opponents) {

        batch.begin();
        batch.setProjectionMatrix(getProjectionMatrix());
        for (OpponentPlayer o : opponents) {
            renderOpponent(batch, o);
            renderOpponentName(batch, o);
        }
        batch.end();
    }

    private float getScaledPosition(float carPosition){
        return carPosition/sizeOfWorld;
    }

    private void renderOpponent(SpriteBatch batch, OpponentPlayer opponent) {
        spriteOpponentVehicle.setPosition((getCameraPosition().x - getViewportWidth()/2.8f)+(getViewportWidth()/7.3f*getScaledPosition(opponent.getPosition().x)), getCameraPosition().y + getViewportHeight()/2.4f);
        spriteOpponentVehicle.setRotation((float) Math.toDegrees(opponent.getRotation()));

        spriteOpponentVehicle.draw(batch);
    }

    private void renderOpponentName(SpriteBatch batch, OpponentPlayer opponent) {
        lblPlayerName.setText(opponent.getName());
        lblPlayerName.setPosition((getCameraPosition().x - getViewportWidth()/2.8f)+(getViewportWidth()/7.3f*getScaledPosition(opponent.getPosition().x)), getCameraPosition().y + getViewportHeight()/2.4f);

        lblPlayerName.draw(batch, 1);
    }


    public void dispose() {
    }

}
