package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.physics.box2d.World;

public class SportsCar extends AbstractCar {

    public SportsCar(World world, float posX, float posY) {
        super(world, posX, posY, new Chassi(15, 5, 10), new Motor(20000, 20, 50*(float)(Math.PI*2)), new Wheels(6, 10, 1, 0.5f));
    }

}
