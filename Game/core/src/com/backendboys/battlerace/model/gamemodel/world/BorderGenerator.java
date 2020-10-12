package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

class BorderGenerator {

    static void generateBorder(World world, Vector2 startVertex, Vector2 endVertex, int height) {

        Vector2 leftVertexTop = new Vector2(startVertex);
        leftVertexTop.y += height;

        Vector2 rightVertexTop = new Vector2(endVertex);
        rightVertexTop.y += height;

        Vector2[] wallVertices = {startVertex, leftVertexTop, rightVertexTop, endVertex};

        ChainShape chainShape = new ChainShape();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;

        chainShape.createChain(wallVertices);
        Body wall = world.createBody(bodyDef);
        wall.createFixture(fixtureDef);
    }
}
