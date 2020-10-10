package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Abstract class that has properties of the powerups.
 */

public abstract class AbstractPowerUp {

    private static float HEIGHT = 4f;
    private static float WIDTH = 4f;

    private Body body;

    /**
     * Instantiates the body of the PowerUp and puts it in the world.
     *
     * @param world     Needed to spawn the powerup in the world.
     * @param spawnPosX The x value of its' spawn position.
     * @param spawnPosY the y value of its' spawn position.
     */
    public AbstractPowerUp(World world, float spawnPosX, float spawnPosY) {
        body = instantiateBody(world, spawnPosX, spawnPosY);
    }

    private Body instantiateBody(World world, float spawnPosX, float spawnPosY) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(HEIGHT, WIDTH);
        fixtureDef.shape = boxShape;

        bodyDef.position.set(spawnPosX, spawnPosY);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData(this);

        // PowerUp is a sensor, no physics collision will occur.
        // But collisions and contact listeners will still be called.
        for (int i = 0; i < body.getFixtureList().size; i++) {
            body.getFixtureList().get(i).setSensor(true);
        }

        boxShape.dispose();

        return body;
    }

    /**
     * What happens when player uses PowerUp.
     *
     * @param player The player that uses the PowerUp.
     */
    public abstract void use(Player player);

    /**
     * Returns name of PowerUp, used for testing purposes.
     *
     * @return Name of PowerUp.
     */
    @Override
    public abstract String toString();

    /**
     * Returns the body of the PowerUp.
     *
     * @return The body of PowerUp.
     */
    public Body getBody() {
        return body;
    }
}
