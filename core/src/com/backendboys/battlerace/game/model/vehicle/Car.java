package com.backendboys.battlerace.game.model.vehicle;

import com.badlogic.gdx.physics.box2d.*;

public class Car {

    private final Body body;
    private float speedX;
    private float speedY;

    public Car(World world, float posX, float posY) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        Body body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(5);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;

        body.createFixture(fixtureDef);
        shape.dispose();

        this.body = body;
    }

    public void gas() {
        body.applyForceToCenter(3000f, 0f, true);
    }

    public void brake() {
        body.applyForceToCenter(-3000f, 0f, true);
    }

}
