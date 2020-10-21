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
public class VehicleRender extends AbstractRender<IVehicle> {

    private static final int VEHICLE_WIDTH = 60;
    private static final int VEHICLE_HEIGHT = 25;
    private static final int VEHICLE_GROUND_OFFSET = 3;
    private static final int WHEEL_SIZE = 12;

    private final Sprite spritePlayerVehicle;
    private final Sprite spriteVehicleFrontWheel;
    private final Sprite spriteVehicleRearWheel;


    public VehicleRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);

        spritePlayerVehicle = new Sprite(new Texture("newredcar.png"));
        spritePlayerVehicle.setSize(VEHICLE_WIDTH, VEHICLE_HEIGHT);
        spritePlayerVehicle.setOriginCenter();

        spriteVehicleFrontWheel = new Sprite(new Texture("wheel.png"));
        spriteVehicleFrontWheel.setSize(WHEEL_SIZE, WHEEL_SIZE);
        spriteVehicleFrontWheel.setOriginCenter();

        spriteVehicleRearWheel = new Sprite(new Texture("wheel.png"));
        spriteVehicleRearWheel.setSize(WHEEL_SIZE, WHEEL_SIZE);
        spriteVehicleRearWheel.setOriginCenter();
    }

    @Override
    public void render(SpriteBatch batch, IVehicle object) {
        batch.begin();
        if (object instanceof ICar) {
            ICar car = (ICar) object;
            spritePlayerVehicle.setPosition(car.getPosition().x - VEHICLE_WIDTH / 2f, car.getPosition().y - VEHICLE_HEIGHT / 2f);
            spritePlayerVehicle.setRotation((float) Math.toDegrees(car.getRotation()));

            spriteVehicleFrontWheel.setPosition(car.getFrontWheelPosition().x - 6f, car.getFrontWheelPosition().y - 5.5f);
            spriteVehicleFrontWheel.setRotation((float) Math.toDegrees(car.getFrontWheelRotation()));

            spriteVehicleRearWheel.setPosition(car.getRearWheelPosition().x - 6f, car.getRearWheelPosition().y - 5.5f);
            spriteVehicleRearWheel.setRotation((float) Math.toDegrees(car.getRearWheelRotation()));

            batch.setProjectionMatrix(getProjectionMatrix());
            spritePlayerVehicle.draw(batch);
            spriteVehicleRearWheel.draw(batch);
            spriteVehicleFrontWheel.draw(batch);
        }
        batch.end();
    }

    public void dispose() {
    }
}
