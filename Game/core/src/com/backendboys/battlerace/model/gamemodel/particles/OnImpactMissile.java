package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

class OnImpactMissile extends AbstractExplosive {
    private final Body body;
    private static final Vector2 direction = new Vector2(2, 0);
    private static final int movementPower = 10;
    private static final float MISSILE_LENGTH = 5f;
    private static final float MISSILE_HEIGHT = 1f;
    private static final float LAUNCH_OFFSET = 30f;
    private static final int NUM_PARTICLES = 30;


    OnImpactMissile(World world, Vector2 pos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true; // A missile can rotate
        bodyDef.bullet = true;
        bodyDef.linearDamping = 0; //air resistance
        bodyDef.gravityScale = 1;
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
        fixtureDef.friction = 100;
        fixtureDef.restitution = 0;
        fixtureDef.filter.groupIndex = -1; // makes particles unable to collide with one another
        body.createFixture(fixtureDef);
        polygonShape.dispose();
    }

    Body getBody() {
        return body;
    }

    static int getNumParticles() {
        return NUM_PARTICLES;
    }

    @Override
    public void explosiveCollided() {
        setToBeRemoved(true);
    }
}
