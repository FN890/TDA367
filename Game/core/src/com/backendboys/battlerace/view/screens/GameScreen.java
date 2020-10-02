package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.model.gamemodel.IModelListener;
import com.backendboys.battlerace.view.game.BackgroundRender;
import com.backendboys.battlerace.view.game.VehicleRender;

import com.backendboys.battlerace.view.game.PowerUpsRender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Class that handles rendering for the game
 */
class GameScreen extends AbstractScreen implements IScreen, IModelListener {

    private final GameController gameController;

    private final BackgroundRender backgroundRender;
    private final VehicleRender vehicleRender;
    private final PowerUpsRender powerUpsRender;

    private final OrthographicCamera camera;
    private final ExtendViewport viewport;
    private final Box2DDebugRenderer debugRenderer;

    GameScreen(GameController gameController) {
        this.gameController = gameController;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(600, 50, camera);
        debugRenderer = new Box2DDebugRenderer();
        backgroundRender = new BackgroundRender(camera, gameController.getGameWorld().getGroundVertices());
        vehicleRender = new VehicleRender(camera);

        powerUpsRender = new PowerUpsRender(camera, gameController.getGameModel().getPowerUps());

    }

    // TODO: 2020-09-20 Decrease exposure of different class structures.
    @Override
    public void render(float delta) {
        super.render(delta);
        gameController.gameStepWorld();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCameraPosition(gameController.getGameModel().getPlayerPosition().x, gameController.getGameModel().getPlayerPosition().y);
        backgroundRender.renderBackground();
        vehicleRender.renderVehicle(gameController.getGameModel().getPlayer().getVehicle());
        debugRenderer.render(gameController.getGameWorld().getWorld(), camera.combined);
        powerUpsRender.renderPowerUps();
        Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        gameController.getGameWorld().dispose();
        debugRenderer.dispose();
        backgroundRender.dispose();
        vehicleRender.dispose();
        powerUpsRender.dispose();
    }

    @Override
    public void update() {
    }

    // TODO: 2020-09-20 Make camera follow y-axis properly
    // TODO: 2020-09-20 Improve camera movement
    private void updateCameraPosition(float x, float y) {
        camera.position.set(x + 200, camera.position.y, camera.position.z);
        camera.update();
    }
}
