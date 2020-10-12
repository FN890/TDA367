package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Class that handles the creation of borders.
 */
class BorderGenerator {

    /**
     * Generates two vertical and one horizontal border. T
     * The horizontal border is created by connecting the two vertical borders highest vertices.
     * @param world The world that will receive the borders.
     * @param startVertex Left vertical border bottom vertex;
     * @param endVertex Right vertical border bottom vertex;
     * @param height The vertical borders height.
     */
    static void generateBorders(World world, Vector2 startVertex, Vector2 endVertex, int height) {

        Vector2 leftVertexTop = new Vector2(startVertex);
        leftVertexTop.y += height;

        Vector2 rightVertexTop = new Vector2(endVertex);
        rightVertexTop.y += height;

        Vector2[] borderVertices = {startVertex, leftVertexTop, rightVertexTop, endVertex};

        ChainShape chainShape = new ChainShape();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;

        chainShape.createChain(borderVertices);
        Body border = world.createBody(bodyDef);
        border.createFixture(fixtureDef);
    }
}
