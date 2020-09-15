package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.physics.box2d.World;

public class SportsCar extends AbstractCar {

    public SportsCar(World world, float posX, float posY) {
        super(world, posX, posY, new Chassi(5, 2, 1000), new Motor(200, 20, (float)(Math.PI*2)), new Wheels(1, 3, 1, 0.5f));
    }

}
