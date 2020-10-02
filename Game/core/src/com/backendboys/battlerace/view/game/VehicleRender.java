package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *  Class for render Vehicle
 */
public class VehicleRender {

    private final SpriteBatch batch;
    private final Sprite spritePlayerVehicle;
    private final OrthographicCamera camera;

    private static final int VEHCILE_WIDTH = 60;
    private static final int VEHCILE_HEIGHT = 25;
    private static final int VEHICLE_GROUND_OFFSET = 3;

    /**
     *
     * @param camera Camera for setting projection matrix
     */
    public VehicleRender(OrthographicCamera camera){
        this.camera = camera;
        batch = new SpriteBatch();
        Texture textureVehicle = new Texture("RedCar.png");
        spritePlayerVehicle = new Sprite(textureVehicle);
        spritePlayerVehicle.setSize(VEHCILE_WIDTH, VEHCILE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();
    }

    /**
     *
     * @param position Position of car
     * @param rotation Rotation of car
     */
    public void renderVehicle(Vector2 position, float rotation){
        batch.begin();
        spritePlayerVehicle.setPosition(position.x-VEHCILE_WIDTH/2f, position.y-VEHCILE_HEIGHT/2f-VEHICLE_GROUND_OFFSET);
        spritePlayerVehicle.setRotation((float) Math.toDegrees(rotation));
        batch.setProjectionMatrix(camera.combined);
        spritePlayerVehicle.draw(batch);
        batch.end();
    }

    /**
     * Disposes the spriteBatch
     */
    public void dispose() {
        batch.dispose();
    }
}
