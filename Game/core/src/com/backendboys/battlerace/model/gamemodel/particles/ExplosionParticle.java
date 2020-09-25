package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ExplosionParticle {
    private int movementPower = 100;
    private Body body;

    public ExplosionParticle(World world, Vector2 vector, Vector2 rayDir) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.bullet = true;
        bodyDef.linearDamping = 0.3f; //air resistance
        bodyDef.gravityScale = 0;
        bodyDef.position.x = vector.x;
        bodyDef.position.y = vector.y;
        rayDir.scl(movementPower);
        bodyDef.linearVelocity.x = rayDir.x;
        bodyDef.linearVelocity.y = rayDir.y;
        body = world.createBody(bodyDef);

        //sets the reference to a ExplosionParticle so that we know how to remove it later
        body.setUserData(this);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.4f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 500;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0.99f;
        fixtureDef.filter.groupIndex = -1; // makes particles unable to collide with eachother
        body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return body;
    }
}
