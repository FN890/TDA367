package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.physics.box2d.World;

/**
 * A Factory that returns a vehicle.
 * Used for encapsulating the creation of vehicle model.
 */
public class VehicleFactory {

    /**
     * @param world the gameWorld, used for creating the physics body of vehicle and adding it to the world.
     * @param posX  x position the vehicle should be placed in world
     * @param posY  y position the vehicle should be placed in world
     * @return Interface IVehicle, a interface for controller the vehicle.
     */
    public static ICar createSportsCar(World world, float posX, float posY) {
        return new SportsCar(world, posX, posY);
    }
}
