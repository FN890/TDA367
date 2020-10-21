package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
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
import java.util.List;

public class PlayerPlacementRender extends AbstractRender<Player> {

    private static final int VEHCILE_WIDTH_ICON = 30;
    private static final int VEHCILE_HEIGHT_ICON = 15;

    private final Sprite spritePlayerVehicle;
    private final Sprite spriteOpponentVehicle;
    private final Label lblPlayerName;
    private final ShapeRenderer shapeRenderer;

    private final float sizeOfWorld;
    private final float lineStartX;
    private final float lineEndX;
    private final float lineYPos;

    public PlayerPlacementRender(OrthographicCamera orthographicCamera, ArrayList<Vector2> groundVertices) {
        super(orthographicCamera);

        spritePlayerVehicle = new Sprite(new Texture("newredcar.png"));
        spritePlayerVehicle.setSize(VEHCILE_WIDTH_ICON, VEHCILE_HEIGHT_ICON);
        spritePlayerVehicle.setOriginCenter();

        spriteOpponentVehicle = new Sprite(new Texture("BlueCar.png"));
        spriteOpponentVehicle.setSize(VEHCILE_WIDTH_ICON, VEHCILE_HEIGHT_ICON);
        spriteOpponentVehicle.setOriginCenter();

        sizeOfWorld = groundVertices.size();
        lineStartX = 0.2f * orthographicCamera.viewportWidth;
        lineEndX = 0.8f * orthographicCamera.viewportWidth;
        lineYPos = 0.9f * orthographicCamera.viewportHeight;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        //shapeRenderer.setAutoShapeType(true);

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        lblPlayerName = new Label("Opponent", uiSkin);
        lblPlayerName.setFontScale(0.5f);
    }

    @Override
    public void render(SpriteBatch batch, Player player) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rectLine(-0.7f, 0.85f, 0.7f, 0.85f, 0.01f);
        shapeRenderer.end();

        batch.begin();
        batch.setProjectionMatrix(getProjectionMatrix());

        IVehicle vehicle = player.getVehicle();
        if (vehicle instanceof ICar) {
            ICar car = (ICar) vehicle;
            spritePlayerVehicle.setPosition((getCameraPosition().x - getViewportWidth()/2.8f)+(getViewportWidth()/7.3f*getScaledPosition(car.getPosition().x)), getCameraPosition().y + getViewportHeight()/2.4f);
            spritePlayerVehicle.setRotation((float) Math.toDegrees(car.getRotation()));

            spritePlayerVehicle.draw(batch);

            lblPlayerName.setText(player.getName());
            lblPlayerName.setPosition((getCameraPosition().x - getViewportWidth()/2.8f)+(getViewportWidth()/7.3f*getScaledPosition(car.getPosition().x)), getCameraPosition().y + getViewportHeight()/2.3f);

            lblPlayerName.draw(batch, 1);
        }

        batch.end();
    }

    private float getScaledPosition(float carPosition){
        return carPosition/sizeOfWorld;
    }

    private void renderOpponent(SpriteBatch batch, OpponentPlayer opponent) {
        spritePlayerVehicle.setPosition(opponent.getPlayerPosition().x - VEHCILE_WIDTH_ICON / 2f, opponent.getPlayerPosition().y - VEHCILE_HEIGHT_ICON / 2f);
        spritePlayerVehicle.setRotation((float) Math.toDegrees(opponent.getPlayerRotation()));

        spritePlayerVehicle.draw(batch);
    }

    private void renderOpponentName(SpriteBatch batch, OpponentPlayer opponent) {
        lblPlayerName.setText(opponent.getPlayerName());
        lblPlayerName.setPosition(opponent.getPlayerPosition().x - VEHCILE_WIDTH_ICON / 2f, opponent.getPlayerPosition().y + 25);

        lblPlayerName.draw(batch, 1);
    }


    public void dispose() {
    }

}
