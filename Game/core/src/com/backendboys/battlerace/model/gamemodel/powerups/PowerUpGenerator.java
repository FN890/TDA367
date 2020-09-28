package com.backendboys.battlerace.model.gamemodel.powerups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Random;

public class PowerUpGenerator {

    ArrayList<Vector2> vertices;
    World world;

    public PowerUpGenerator(ArrayList<Vector2> vertices, World world) {
        this.vertices = vertices;
        this.world = world;
    }

    public ArrayList<AbstractPowerUp> generatePowerups(int numberPowerups) {

        ArrayList<AbstractPowerUp> powerUps = new ArrayList<>();

        int space = vertices.size() / numberPowerups;
        int positionX = space + 100;

        Random random = new Random();

        for (int i = 0; i < numberPowerups; i++) {

            positionX -= random.nextInt(100);
            Vector2 tempVector = vertices.get(positionX / 5);
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

        return powerUps;

    }
}
