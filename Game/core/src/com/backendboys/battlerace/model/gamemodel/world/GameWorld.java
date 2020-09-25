package com.backendboys.battlerace.model.gamemodel.world;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    private World world;
    private GroundGenerator groundGenerator;
    private Box2DDebugRenderer debugRenderer;

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
        generatePowerups(30);
    }

    public void stepWorld() {

        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
        BodyRemover.removeBodies(world);
        checkCollision();
    }

    private void checkCollision() {

    }
    
    private void generatePowerups(int numberPowerups) {
        int space = groundGenerator.getNumberVertices() / numberPowerups;
        int positionX = space;

        Random random = new Random();

        for (int i = 0; i < numberPowerups; i++) {

            positionX -= random.nextInt(100);
            Vector2 tempVector = groundGenerator.getVertices().get(positionX / 5);
            int positionY = (int) tempVector.y + 30;

            if (random.nextInt(2) == 1) {
                NitroPowerUp nitroPowerUp = new NitroPowerUp();
                nitroPowerUp.InstantiateBody(world, positionX, positionY);
                powerUps.add(nitroPowerUp);

            } else {
                MissilePowerUp missilePowerUp = new MissilePowerUp();
                missilePowerUp.InstantiateBody(world, positionX, positionY);
                powerUps.add(missilePowerUp);
            }
            positionX += space;
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

}
