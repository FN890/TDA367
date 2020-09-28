package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.physics.box2d.World;

public class VehicleFactory {

    public static IVehicle createSportsCar(World world, float posX, float posY) {
        return new SportsCar(world, posX, posY);
    }
}
