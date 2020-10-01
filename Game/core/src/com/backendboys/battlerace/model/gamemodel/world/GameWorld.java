package com.backendboys.battlerace.model.gamemodel.world;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.PowerUpGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing the game world.
 */
public class GameWorld {

    private final World world;
    private final GroundGenerator groundGenerator;

    //List with all powerups in world
    private ArrayList<AbstractPowerUp> powerUps;

    private float accumulator;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    public GameWorld() {
        Box2D.init();
        world = new World(new Vector2(0, -100), true);
        groundGenerator = new GroundGenerator(10000, 5, 1);
        groundGenerator.generateGround(world);
        powerUps = new ArrayList<>();

        PowerUpGenerator powerUpGenerator = new PowerUpGenerator(groundGenerator.getVertices(), world);
        powerUps = powerUpGenerator.generatePowerups(30);
    }

    /**
     * Step the world if enough time has passed since last step.
     */
    public void stepWorld() {

        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
        checkCollision();
    }

    //TODO Check collision between car and powerup in world
    //TODO Remove powerup and give the powerup to the player
    //Collision handling will have its' own class in future versions
    private void checkCollision() {
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

}
