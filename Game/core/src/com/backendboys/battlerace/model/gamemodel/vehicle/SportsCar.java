package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.physics.box2d.World;

/**
 * SportsCar is a specific implementation of a AbstractCar.
 */
public class SportsCar extends AbstractCar {

    /**
     * @param world the gameWorld, used for creating the physics body of vehicle and adding it to the world.
     * @param posX  x position the vehicle should be placed in world
     * @param posY  y position the vehicle should be placed in world
     */
    public SportsCar(World world, float posX, float posY) {
        super(world, posX, posY, new Chassi(30, 8, 10), new Motor(5, 10, 3 * (float) (Math.PI * 2)), new Wheels(6, 20, 2, 0.2f));
    }

}
