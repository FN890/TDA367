package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Stack;

/**
 * Abstract class that has properties of the powerups.
 */
abstract class AbstractPowerUp implements IPowerUp{

    private final static float HEIGHT = 4f;
    private final static float WIDTH = 4f;

    private final Body body;

    private final Stack<Body> bodiesToRemove;

    /**
     * Instantiates the body of the PowerUp and puts it in the world.
     *
     * @param world     Needed to spawn the powerup in the world.
     * @param spawnPosX The x value of its' spawn position.
     * @param spawnPosY the y value of its' spawn position.
     */
    public AbstractPowerUp(World world, float spawnPosX, float spawnPosY) {
        body = instantiateBody(world, spawnPosX, spawnPosY);
        bodiesToRemove = new Stack<>();
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

    @Override
    public void remove() {
        bodiesToRemove.add(body);
    }

    @Override
    public void onGameWorldStepped() {
        World world = body.getWorld();

        while (bodiesToRemove.size() > 0) {
            world.destroyBody(bodiesToRemove.pop());
        }
    }

    /**
     * Returns name of PowerUp, used for testing purposes.
     *
     * @return Name of PowerUp.
     */
    @Override
    public abstract String toString();

    public Vector2 getPosition() {
        return body.getPosition();
    }
}
