package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.model.gamemodel.GameModel;
import com.backendboys.battlerace.model.gamemodel.IModelListener;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.view.game.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Class that handles rendering for the game
 */
class GameScreen extends AbstractScreen implements IScreen, IModelListener {

    private final GameWorld gameWorld;
    private final GameModel gameModel;
    private final GameController gameController;

    private final BackgroundRender backgroundRender;
    private final VehicleRender vehicleRender;
    private final PowerUpsRender powerUpsRender;
    private final MissileRender missileRender;
    private final FinishLineRender finishLineRender;

    private final OrthographicCamera camera;
    private final ExtendViewport viewport;
    private final Box2DDebugRenderer debugRenderer;

    GameScreen(GameController gameController) {

        this.gameController = gameController;
        gameModel = gameController.getGameModel();
        gameWorld = gameModel.getGameWorld();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(600, 50, camera);
        debugRenderer = new Box2DDebugRenderer();
        backgroundRender = new BackgroundRender(camera, gameWorld.getGroundVertices());
        vehicleRender = new VehicleRender(camera);
        missileRender = new MissileRender(camera);
        powerUpsRender = new PowerUpsRender(camera, gameModel.getPowerUps());
        finishLineRender = new FinishLineRender(camera, gameWorld.getFinishLineVertices());

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameController.gameStepWorld();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCameraPosition(gameModel.getPlayerPosition().x, gameModel.getPlayerPosition().y);
        backgroundRender.renderBackground();
        vehicleRender.renderVehicle(gameModel.getPlayer().getVehicle());
        debugRenderer.render(gameWorld.getWorld(), camera.combined);
        missileRender.render(gameWorld.getMissiles());
        powerUpsRender.renderPowerUps();
        finishLineRender.renderFinishLine();
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
        missileRender.dispose();
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
