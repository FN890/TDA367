package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * A Missile that explodes on impact created in WorldExplosions
 */
class OnImpactMissile extends AbstractExplosive {
    private final Body body;
    private static final Vector2 direction = new Vector2(10, 4);
    private static final float MOVEMENT_POWER = 100;
    private static final float MISSILE_LENGTH = 5f;
    private static final float MISSILE_HEIGHT = 1f;
    private static final float LAUNCH_OFFSET_Y = 50f;
    private static final float LAUNCH_OFFSET_x = 0f;
    private static final int NUM_PARTICLES = 30;

    /**
     * constructor - creates the Missile
     *
     * @param world the world where it spawns
     * @param pos   spawn point of the missile
     */
    OnImpactMissile(World world, Vector2 pos, float rotation) {
        rotation = rotation + (MathUtils.PI / 2);
        System.out.println("Rotation: " + rotation);
        direction.x = rotation * MathUtils.sin(rotation);
        direction.y = -rotation * MathUtils.cos(rotation);
        System.out.println("x: " + direction.x);
        System.out.println("y: " + direction.y);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false; // A missile can rotate
        bodyDef.bullet = false;
        bodyDef.linearDamping = 0; //air resistance
        bodyDef.gravityScale = 1;
        bodyDef.position.x = pos.x + LAUNCH_OFFSET_x;
        bodyDef.position.y = pos.y + LAUNCH_OFFSET_Y;
        direction.scl(MOVEMENT_POWER);
        bodyDef.linearVelocity.x = direction.x ;
        bodyDef.linearVelocity.y = direction.y ;
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
        body.createFixture(fixtureDef).setUserData(this);
        polygonShape.dispose();
    }

    Body getBody() {
        return body;
    }

    static int getNumParticles() {
        return NUM_PARTICLES;
    }

    /**
     * After the Missile collides we give it permission to be removed -
     * from the world and the WorldExplosions class
     */
    @Override
    public void explosiveCollided() {
        setToBeRemoved(true);
    }
}
