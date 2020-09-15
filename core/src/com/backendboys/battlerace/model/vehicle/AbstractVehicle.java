package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

abstract class AbstractVehicle {

    private static final float FRICTION = 0.5f;
    private static final float RESTITUTION = 0.6f;

    private Body body;

    private final float topSpeed;
    private final float acceleration;
    private final float angularAcceleration;

    protected AbstractVehicle(World world, float mass, float posX, float posY, float topSpeed, float acceleration, float angularAcceleration) {
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.angularAcceleration = angularAcceleration;

        instantiateBody(world, mass, posX, posY);
    }

    private void instantiateBody(World world, float mass, float posX, float posY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        Body body = world.createBody(bodyDef);

        Shape shape = createVehicleShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createVehicleShape();
        fixtureDef.density = (float) (mass/(Math.pow(2, shape.getRadius())*Math.PI));
        fixtureDef.friction = FRICTION;
        fixtureDef.restitution = RESTITUTION;

        body.createFixture(fixtureDef);
        shape.dispose();

        this.body = body;
    }

    protected abstract Shape createVehicleShape();

    public void gas() {
        if (body.getLinearVelocity().x > topSpeed) {
            return;
        }
        float force = acceleration * body.getMass();
        body.applyForceToCenter(force, 0f, true);
    }

    public void brake() {
        if (body.getLinearVelocity().x < 0) {
            return;
        }
        float force = -acceleration * body.getMass() * 0.5f;
        body.applyForceToCenter(force, 0f, true);
    }

    public void rotateRight() {
        if (body.getAngularVelocity() < -1 * 2*Math.PI) {
            return;
        }
        float torque = -angularAcceleration * body.getMassData().I;
        body.applyTorque(torque, true);
    }

    public void rotateLeft() {
        if (body.getAngularVelocity() > 1 * 2 * Math.PI) {
            return;
        }
        float torque = angularAcceleration * body.getMassData().I;
        body.applyTorque(torque, true);
    }

    public Vector2 getPosition() {
        return new Vector2(body.getPosition());
    }

    public void setPosition(Vector2 pos) {
        body.getPosition().set(pos);
    }

}
