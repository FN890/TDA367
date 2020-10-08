package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.vehicle.ICar;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class for render Vehicle
 */
public class VehicleRender {

    private final SpriteBatch batch;
    private final Sprite spritePlayerVehicle;
    private final Sprite spriteVehicleFrontWheel;
    private final Sprite spriteVehicleRearWheel;
    private final OrthographicCamera camera;

    private static final int VEHCILE_WIDTH = 60;
    private static final int VEHCILE_HEIGHT = 25;
    private static final int VEHICLE_GROUND_OFFSET = 3;

    private static final int WHEEL_SIZE = 12;

    /**
     * @param camera Camera for setting projection matrix
     */
    public VehicleRender(OrthographicCamera camera) {
        this.camera = camera;
        batch = new SpriteBatch();
        spritePlayerVehicle = new Sprite(new Texture("newredcar.png"));
        spritePlayerVehicle.setSize(VEHCILE_WIDTH, VEHCILE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();

        spriteVehicleFrontWheel = new Sprite(new Texture("wheel.png"));
        spriteVehicleFrontWheel.setSize(WHEEL_SIZE, WHEEL_SIZE);
        spriteVehicleFrontWheel.setOriginCenter();

        spriteVehicleRearWheel = new Sprite(new Texture("wheel.png"));
        spriteVehicleRearWheel.setSize(WHEEL_SIZE, WHEEL_SIZE);
        spriteVehicleRearWheel.setOriginCenter();
    }

    /**
     * @param vehicle vehicle interface with position and rotation for render
     */
    public void renderVehicle(IVehicle vehicle) {
        batch.begin();
        if(vehicle instanceof ICar) {
            ICar car = (ICar) vehicle;
            spritePlayerVehicle.setPosition(car.getPosition().x - VEHCILE_WIDTH / 2f, car.getPosition().y - VEHCILE_HEIGHT / 2f);
            spritePlayerVehicle.setRotation((float) Math.toDegrees(car.getRotation()));

            spriteVehicleFrontWheel.setPosition(car.getFrontWheelPosition().x - 6f, car.getFrontWheelPosition().y - 5.5f);
            spriteVehicleFrontWheel.setRotation((float) Math.toDegrees(car.getFrontWheelRotation()));

            spriteVehicleRearWheel.setPosition(car.getRearWheelPosition().x - 6f, car.getRearWheelPosition().y - 5.5f);
            spriteVehicleRearWheel.setRotation((float) Math.toDegrees(car.getRearWheelRotation()));

            batch.setProjectionMatrix(camera.combined);
            spritePlayerVehicle.draw(batch);
            spriteVehicleRearWheel.draw(batch);
            spriteVehicleFrontWheel.draw(batch);
        }
        batch.end();
    }

    /**
     * Disposes the spriteBatch
     */
    public void dispose() {
        batch.dispose();
    }
}
