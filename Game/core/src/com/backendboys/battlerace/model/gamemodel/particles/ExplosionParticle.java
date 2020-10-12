package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * A particle for explosions one instance is one particle
 */
class ExplosionParticle extends AbstractExplosive implements IParticle {

    private final Body body;
    private static final int movementPower = 200;

    /**
     * Constructor for a particle
     * Adds a particle to the world
     *
     * @param world  The world where the particle is created
     * @param pos    used to determine the position of the particle
     * @param rayDir Used to determine which direction the particle is travelling
     */
    ExplosionParticle(World world, Vector2 pos, Vector2 rayDir) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.bullet = true;
        bodyDef.linearDamping = 0.3f; //air resistance
        bodyDef.gravityScale = 1;
        bodyDef.position.x = pos.x;
        bodyDef.position.y = pos.y;
        rayDir.scl(movementPower);
        bodyDef.linearVelocity.x = rayDir.x;
        bodyDef.linearVelocity.y = rayDir.y;
        body = world.createBody(bodyDef);

        body.setUserData(this);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(2.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 100;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0.99f;
        fixtureDef.filter.groupIndex = -1; // makes particles unable to collide with one another
        body.createFixture(fixtureDef).setUserData(this);
        circleShape.dispose();

    }

    Body getBody() {
        return body;
    }

    @Override
    public void explosiveCollided() {
        setToBeRemoved(true);

    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getRotation() {
        return 0;
    }
}
