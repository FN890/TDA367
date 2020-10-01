package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.List;

/**
 * Abstract class that has properties that a vehicle would be expected to have.
 */
abstract class AbstractVehicle implements IVehicle {

    private static final float FRICTION = 0.5f;
    private static final float RESTITUTION = 0.6f;

    private final float mass;
    private final float spawnPosX;
    private final float spawnPosY;

    private Body body;
    private List<Body> parts;

    private final float topSpeed;
    private final float acceleration;
    private final float angularAcceleration;

    protected AbstractVehicle(float mass, float spawnPosX, float spawnPosY, float topSpeed, float acceleration, float angularAcceleration) {
        this.mass = mass;
        this.spawnPosX = spawnPosX;
        this.spawnPosY = spawnPosY;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.angularAcceleration = angularAcceleration;
    }

    protected void build(World world) {
        instantiateBody(world, mass, spawnPosX, spawnPosY);
        parts = initParts(body, world);
    }

    private void instantiateBody(World world, float mass, float posX, float posY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        Body body = world.createBody(bodyDef);

        Shape shape = createVehicleShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createVehicleShape();
        fixtureDef.density = (mass / (vehicleArea()));
        fixtureDef.friction = FRICTION;
        fixtureDef.restitution = RESTITUTION;

        body.createFixture(fixtureDef);
        shape.dispose();

        this.body = body;
    }

    protected abstract Shape createVehicleShape();

    protected abstract float vehicleArea();

    protected abstract List<Body> initParts(Body main, World world);

    /**
     * Apply a forward force to the center of vehicle if speed < topSpeed.
     */
    public void gas() {
        if (body.getLinearVelocity().x >= topSpeed) {
            return;
        }
        float force = acceleration * body.getMass();
        body.applyForceToCenter(force, 0f, true);
    }

    /**
     * Apply a backwards force to the center of vehicle if speed > 0.
     */
    public void brake() {
        if (body.getLinearVelocity().x <= 0) {
            return;
        }
        float force = -acceleration * body.getMass() * 0.5f;
        body.applyForceToCenter(force, 0f, true);
    }

    /**
     * Apply clockwise torque to the center of vehicle
     */
    public void rotateRight() {
        if (body.getAngularVelocity() < -1 * 2 * Math.PI) {
            return;
        }
        float torque = -angularAcceleration * body.getMassData().I;
        body.applyTorque(torque, true);
    }

    /**
     * Apply counter-clockwise torque to the center of vehicle
     */
    public void rotateLeft() {
        if (body.getAngularVelocity() > 1 * 2 * Math.PI) {
            return;
        }
        float torque = angularAcceleration * body.getMassData().I;
        body.applyTorque(torque, true);
    }

    /**
     * @return Position of center mass of the body.
     */
    public Vector2 getWorldCenter() {
        return new Vector2(body.getWorldCenter());
    }

    /**
     * @return Vehicle position.
     */
    public Vector2 getPosition() {
        return new Vector2(body.getPosition());
    }

    /**
     * @param pos Changes the position of vehicle.
     */
    public void setPosition(Vector2 pos) {
        body.getPosition().set(pos);
    }

}
