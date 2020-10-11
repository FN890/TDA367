package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

class WallGenerator {

    static void generateWall(World world, Vector2 vertex, int height) {

        Vector2 vertexTop = new Vector2(vertex);
        vertexTop.y += height;

        Vector2[] wallVertices = {vertex, vertexTop};

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
