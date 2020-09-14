package com.backendboys.battlerace.game.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

class Car extends AbstractVehicle {

    //TODO: Implement as Basic car, should be able to have subclasses: Sportscar etc.
    protected Car(World world, float mass, float posX, float posY, float topSpeed, float acceleration, float angularAcceleration) {
        super(world, mass, posX, posY, topSpeed, acceleration, angularAcceleration);
    }

    @Override
    protected Shape createVehicleShape() {
        return null;
    }

}
