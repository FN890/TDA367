package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.physics.box2d.World;

public class SportsCar extends AbstractCar {

    public SportsCar(World world, float posX, float posY) {
        super(world, posX, posY, new Chassi(6, 4, 100), new Motor(2000, 20, (float)(Math.PI*2)), new Wheels(7, 10, 5, 0.5f));
    }

}
