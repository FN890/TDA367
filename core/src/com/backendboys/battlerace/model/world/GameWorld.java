package com.backendboys.battlerace.model.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.HashMap;

public class GameWorld {

    private final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private TextureAtlas textureAtlas;
    private SpriteBatch batch;

    private World world;
    private GroundGenerator groundGenerator;
    private Box2DDebugRenderer debugRenderer;

    private float accumulator;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final float SCALE = 0.05f;


    public GameWorld() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(200, 50, camera);

        Box2D.init();

        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        groundGenerator = new GroundGenerator(10000, 1);

        debugRenderer = new Box2DDebugRenderer();

    }

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        groundGenerator.generateGround(world);
        batch.setProjectionMatrix(camera.combined);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stepWorld();
        batch.begin();
        batch.end();
        debugRenderer.render(world, camera.combined);
    }

    public void dispose() {
        textureAtlas.dispose();
        sprites.clear();
        world.dispose();
        debugRenderer.dispose();
    }


}
