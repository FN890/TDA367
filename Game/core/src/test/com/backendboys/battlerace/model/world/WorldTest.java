package com.backendboys.battlerace.model.world;

import com.backendboys.battlerace.model.world.ground.GroundStrategyFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {

    @Test
    public void verticesTest() {
        Random random = new Random();
        int verticesAmount = random.nextInt(1000);
        GameWorld gameWorld = new GameWorld(GroundStrategyFactory.getSinCosATanStrategy(verticesAmount, 60, 5), 1);
        assertEquals(gameWorld.getGroundVertices().size(), verticesAmount);
    }

    @Test
    public void groundGeneratorTest() {
        Random random = new Random();
        int verticesAmount = random.nextInt(1000);
        World world = new World(new Vector2(0, -10), true);
        GroundGenerator groundGenerator = new GroundGenerator();
        groundGenerator.generateGround(world, GroundStrategyFactory.getSinCosATanStrategy(verticesAmount, 1000, 5), 1);
        assertEquals(groundGenerator.getVertices().size(), verticesAmount);
    }
}
