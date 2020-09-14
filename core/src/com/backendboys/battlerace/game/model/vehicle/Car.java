package com.backendboys.battlerace.game.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Car {

    private final Body body;
    private final float topSpeed;
    private final float acceleration;
    private final float mass;
    private final float angularAcceleration;

    public Car(World world, float posX, float posY) {

        topSpeed = 200;
        acceleration = 500;
        mass = 1000;
        angularAcceleration = (float) (3 * Math.PI * 2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        Body body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(5);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = (float) (1000f/(shape.getRadius()*shape.getRadius() * Math.PI));
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();

        this.body = body;
        System.out.println(body.getMass());
    }

    public void gas() {
        if (body.getLinearVelocity().x > topSpeed) {
            return;
        }
        float force = acceleration * body.getMass();
        body.applyForceToCenter(force, 0f, true);
        System.out.println(body.getLinearVelocity().x);
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

    public Vector2 getPos() {
        return new Vector2(body.getPosition());
    }

    public void setPos(Vector2 pos) {
        body.getPosition().set(pos);
    }

}
