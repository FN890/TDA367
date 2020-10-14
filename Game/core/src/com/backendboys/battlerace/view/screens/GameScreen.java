package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.ServerController;
import com.backendboys.battlerace.model.gamemodel.GameModel;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.view.game.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Class that handles rendering for the game
 */
class GameScreen extends AbstractScreen implements IScreen {

    private final GameWorld gameWorld;
    private final GameModel gameModel;
    private final GameController gameController;
    private ServerController serverController = null;

    private final OrthographicCamera camera;
    private final ExtendViewport viewport;
    private final SpriteBatch batch;

    private final BackgroundRender backgroundRender;
    private final VehicleRender vehicleRender;
    private final OpponentRender opponentRender;
    private final PowerUpsRender powerUpsRender;
    private final MissileRender missileRender;
    private final ExplosionParticleRender explosionParticleRender;
    private final Box2DDebugRenderer debugRenderer;
    private final FinishLineRender finishLineRender;

    GameScreen(GameController gameController) {

        this.gameController = gameController;
        gameModel = gameController.getGameModel();
        gameWorld = gameModel.getGameWorld();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(600, 50, camera);

        backgroundRender = new BackgroundRender(camera, gameWorld.getGroundVertices());
        vehicleRender = new VehicleRender(camera);
        debugRenderer = new Box2DDebugRenderer();
        opponentRender = new OpponentRender(camera);
        missileRender = new MissileRender(camera);
        explosionParticleRender = new ExplosionParticleRender(camera);
        powerUpsRender = new PowerUpsRender(camera, gameModel.getPowerUps());
        finishLineRender = new FinishLineRender(camera, gameModel.getFinishLineVertices());

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameController.gameStepWorld();
        sendPositionPackets(gameModel.getPlayer());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCameraPosition(gameModel.getPlayerPosition().x, gameModel.getPlayerPosition().y);
        renderData();
        Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
    }

    private void renderData() {
        backgroundRender.render(batch, null);
        vehicleRender.render(batch, gameModel.getPlayer().getVehicle());
        opponentRender.render(batch, gameModel.getOpponents());
        debugRenderer.render(gameWorld.getWorld(), camera.combined);
        missileRender.render(batch, gameModel.getMissiles());
        explosionParticleRender.render(batch, gameModel.getExplosionParticles());
        powerUpsRender.render(batch, null);
        finishLineRender.render(batch, null);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        gameController.getGameWorld().dispose();
        backgroundRender.dispose();
        debugRenderer.dispose();
        opponentRender.dispose();
        vehicleRender.dispose();
        missileRender.dispose();
        explosionParticleRender.dispose();
        powerUpsRender.dispose();
        finishLineRender.dispose();
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    private void sendPositionPackets(Player player) {
        if (serverController != null) {
            serverController.sendPositionPacket(player);
        }
    }

    // TODO: 2020-09-20 Make camera follow y-axis properly
    // TODO: 2020-09-20 Improve camera movement
    private void updateCameraPosition(float x, float y) {
        camera.position.set(x + 200, camera.position.y, camera.position.z);
        camera.update();
    }
}
