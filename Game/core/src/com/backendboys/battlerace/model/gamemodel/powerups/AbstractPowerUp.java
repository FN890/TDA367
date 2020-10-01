package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Abstract class that has properties of the powerups.
 */

public abstract class AbstractPowerUp {

    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public AbstractPowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
    }

    /**
     * Creates the body of the powerup and puts in the world.
     *
     * @param world     Needed to spawn the powerup in the world.
     * @param spawnPosx The x value of its' spawn position.
     * @param spawnPosy the y value of its' spawn position.
     */

    public void InstantiateBody(World world, float spawnPosx, float spawnPosy) {
        bodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(4f, 4f);
        fixtureDef.shape = boxShape;

        bodyDef.position.set(spawnPosx, spawnPosy);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        // PowerUp is a sensor, no physics collision will occur.
        // But collisions and contact listeners will still be called.
        for(int i=0; i<body.getFixtureList().size; i++) {
            body.getFixtureList().get(i).setSensor(true);
        }

        boxShape.dispose();
    }

    /**
     * Gives the powerup to the player who picked it up.
     *
     * @param player The player that picked up the powerup
     */
    protected void onPickup(Player player) {
        player.addPowerUp(this);
    }

    public abstract void use(Player player);

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }
}
