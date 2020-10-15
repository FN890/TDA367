package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that handles creation of powerups.
 */

public class PowerUpGenerator {

    private final ArrayList<Vector2> vertices;
    private final World world;

    private static final int STEP = 5;
    private final WorldExplosions worldExplosions;

    /**
     * @param vertices List of vertices in world to measure spawnpoints of powerups.
     * @param world    The world, needed to spawn the powerups in the world.
     */

    public PowerUpGenerator(ArrayList<Vector2> vertices, World world, WorldExplosions worldExplosions) {
        this.vertices = vertices;
        this.world = world;
        this.worldExplosions = worldExplosions;
    }

    /**
     * Generates the powerups and spawns them in the world.
     *
     * @param numberPowerups The number of powerups to create.
     * @return Returns a list of all powerups in world, needed for collision checking.
     */

    public ArrayList<AbstractPowerUp> generatePowerups(int numberPowerups) {

        ArrayList<AbstractPowerUp> powerUps = new ArrayList<>();

        final int space = vertices.size() / numberPowerups;
        int positionX = space * 2;

        Random random = new Random();

        for (int i = 0; i < numberPowerups; i++) {

            if ((positionX / 5) > vertices.size() - 1) {
                return powerUps;
            }

            Vector2 tempVector = vertices.get(positionX / STEP);
            int positionY = (int) tempVector.y + 20;

            if (random.nextBoolean()) {
                NitroPowerUp nitroPowerUp = new NitroPowerUp(world, positionX, positionY);
                powerUps.add(nitroPowerUp);

            } else {
                MissilePowerUp missilePowerUp = new MissilePowerUp(world, positionX, positionY, worldExplosions);
                powerUps.add(missilePowerUp);
            }
            positionX += space * STEP;
        }

        return powerUps;
    }
}
