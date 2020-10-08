package com.backendboys.battlerace.model.gamemodel.world;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.PowerUpGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Class representing the game world.
 */
public class GameWorld {

    private static final int SPEED_SCALE = 3;
    private final World world;
    private final GroundGenerator groundGenerator;

    private final ArrayList<AbstractPowerUp> powerUps;

    private float accumulator;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    private final WorldExplosions worldExplosions = new WorldExplosions();

    public GameWorld() {
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        groundGenerator = new GroundGenerator(10000, 5, 1);
        groundGenerator.generateGround(world);
        world.setContactListener(new CollisionListener());
        final PowerUpGenerator powerUpGenerator = new PowerUpGenerator(groundGenerator.getVertices(), world);
        powerUps = powerUpGenerator.generatePowerups(30);
    }

    /**
     * Step the world if enough time has passed since last step.
     */
    public void stepWorld() {
        for (int i = 0; i < SPEED_SCALE; i++) {
            float delta = Gdx.graphics.getDeltaTime();
            accumulator += Math.min(delta, 0.25f);
            if (accumulator >= STEP_TIME) {
                accumulator -= STEP_TIME;
                world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            }
        }
        worldExplosions.removeCollidedMissilesAndParticles();
    }

    public void dispose() {
        world.dispose();
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Vector2> getGroundVertices() {
        return groundGenerator.getVertices();
    }


    /**
     * creates a Missile in the world
     *
     * @param pos Spawn Position for the Missile
     */
    public void addMissile(Vector2 pos, float rotation) {
        worldExplosions.addMissile(pos, world, rotation);
        System.out.println("Explosions: " + worldExplosions.getNumberOffExplosions());
        System.out.println("particles: " + worldExplosions.getTotalExplosionParticles());

    }

    public ArrayList<AbstractPowerUp> getPowerUps() {
        return powerUps;
    }

    public ArrayList<IParticle> getMissiles() {
        return worldExplosions.getMissiles();
    }

    public int getNumberOfPowerUps() {
        return powerUps.size();
    }


}
