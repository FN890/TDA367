package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

class OnImpactMissile {
    private final Body body;
    private static final Vector2 direction = new Vector2(2, 1);
    private static final int movementPower = 2000;
    private static final float MISSILE_LENGTH = 5f;
    private static final float MISSILE_HEIGHT = 1f;
    private static final float LAUNCH_OFFSET = 10f;


    OnImpactMissile(World world, Vector2 pos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false; // A missle can rotate
        bodyDef.bullet = true;
        bodyDef.linearDamping = 0.3f; //air resistance
        bodyDef.gravityScale = 0;
        bodyDef.position.x = pos.x;
        bodyDef.position.y = pos.y + LAUNCH_OFFSET;
        direction.scl(movementPower);
        bodyDef.linearVelocity.x = direction.x;
        bodyDef.linearVelocity.y = direction.y;
        body = world.createBody(bodyDef);

        body.setUserData(this);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(MISSILE_LENGTH, MISSILE_HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 100;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0;
        fixtureDef.filter.groupIndex = -1; // makes particles unable to collide with one another
        body.createFixture(fixtureDef);
        polygonShape.dispose();
    }

    public Body getBody() {
        return body;
    }
}
