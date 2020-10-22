package com.backendboys.battlerace.model.world;


import com.backendboys.battlerace.model.world.ground.IGroundStrategy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game world.
 */
public class GameWorld {

    private float accumulator;
    private static final int SPEED_SCALE = 3;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    private final World world;
    private final GroundGenerator groundGenerator;
    private final FinishLineGenerator finishLineGenerator;
    private final static int BORDER_HEIGHT = 10000;

    private final ArrayList<GameWorldListener> gameWorldListeners;

    public GameWorld(IGroundStrategy iGroundStrategy, int friction) {
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        groundGenerator = new GroundGenerator();
        gameWorldListeners = new ArrayList<>();
        groundGenerator.generateGround(world, iGroundStrategy, friction);
        BorderGenerator.generateBorders(world, getGroundVertices().get(0), getGroundVertices().get(getGroundVertices().size() - 1), BORDER_HEIGHT);
        finishLineGenerator = new FinishLineGenerator(getGroundVertices());
        finishLineGenerator.generateFinishLine(world);

    }

    public void setCollisionListener(ContactListener contactListener) {
        world.setContactListener(contactListener);
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
                notifyGameWorldListeners();
            }
        }


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

    public void addListener(GameWorldListener gameWorldListener) {
        gameWorldListeners.add(gameWorldListener);
    }

    public void removeListener(GameWorldListener gameWorldListener) {
        gameWorldListeners.remove(gameWorldListener);
    }

    public List<Vector2> getFinishLineVertices() {
        return finishLineGenerator.getFinishLineVertices();
    }

    private void notifyGameWorldListeners() {
        for (GameWorldListener gameWorldListener : gameWorldListeners) {
            gameWorldListener.onGameWorldStepped();
        }
    }

}
