package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.GameModel;
import com.backendboys.battlerace.model.collisions.CollisionHandler;
import com.backendboys.battlerace.model.collisions.IFinishLineListener;
import com.backendboys.battlerace.model.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.particles.IMissileListener;
import com.backendboys.battlerace.model.particles.ParticleHandler;
import com.backendboys.battlerace.model.world.GameWorld;
import com.backendboys.battlerace.view.screens.IGameScreen;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that handles inputs
 */
public class GameController implements InputProcessor, IMissileListener, IFinishLineListener {

    private final GameModel gameModel;
    private final BattleRace game;

    private final List<Integer> keysDown;

    private boolean usedPowerUp = false;

    private ServerController serverController;

    private final IGameScreen gameScreen;

    /**
     * @param game Created GameModel and set GameScreen.
     */
    public GameController(BattleRace game, boolean isMultiPlayer) {
        gameModel = new GameModel();

        ParticleHandler particleHandler = gameModel.getWorldExplosions();
        particleHandler.addMissileListener(this);

        CollisionHandler collisionHandler = gameModel.getCollisionHandler();
        collisionHandler.addFinishLineListener(this);

        this.game = game;
        keysDown = new ArrayList<>();

        gameScreen = ScreenFactory.createGameScreen(this, false);

        if (isMultiPlayer) {
            serverController = new ServerController(this);
            gameScreen.setServerController(serverController);
        }
    }

    public void showScreen() {
        Gdx.input.setInputProcessor(this);
        game.setScreen(gameScreen);
    }

    public void handleAddOpponent(OpponentPlayer opponent) {
        gameModel.addOpponent(opponent);
    }

    public void handleRemoveOpponent(String name) {
        gameModel.removeOpponent(name);
    }

    public void handleUpdateOpponentPosition(String name, float x, float y, float rotation) {
        gameModel.updateOpponentPosition(name, x, y, rotation);
    }

    /**
     * Calls stepWorld in GameModel
     */
    public void gameStepWorld() {
        gameModel.getGameWorld().stepWorld();
        handleKeyPresses();
    }

    private void handleKeyPresses() {
        for (int keycode : keysDown) {
            switch (keycode) {
                case Input.Keys.W:
                    gameModel.gas();
                    break;
                case Input.Keys.A:
                    gameModel.rotateLeft();
                    break;
                case Input.Keys.S:
                    gameModel.brake();
                    break;
                case Input.Keys.D:
                    gameModel.rotateRight();
                    break;
                case Input.Keys.ESCAPE:
                    exitGame();
                    break;
                case Input.Keys.SPACE:
                    if (!usedPowerUp) {
                        usedPowerUp = true;
                        gameModel.usePowerUp();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void exitGame() {
        if (serverController != null) {
            serverController.disconnect();
        }
        game.startMenu();
    }

    @Override
    public boolean keyDown(int keycode) {
        keysDown.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            usedPowerUp = false;
        }
        keysDown.removeAll(Arrays.asList(keycode));
        return true;
    }

    /**
     * Handles key inputs.
     *
     * @param character the key typed.
     * @return is keyTyped was handled correctly.
     */
    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    public GameWorld getGameWorld() {
        return gameModel.getGameWorld();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public ServerController getServerController() {
        return serverController;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void onMissileShot(Vector2 position, Vector2 velocity, float rotation) {
        if (serverController != null) {
            serverController.sendMissile(position, velocity, rotation);
        }
    }

    @Override
    public void onTouchedFinishLine() {
        if (!gameModel.isGameWon()) {
            gameModel.setGameWon(true);
            if (serverController != null) {
                serverController.sendMessage("win");
            }
        }
    }

    public void setGameWon(boolean gameWon) {
        gameModel.setGameWon(gameWon);
    }

    public String getWinnerName() {
        return gameModel.getWinnerName();
    }

    void setWinnerName(String name) {
        gameModel.setWinnerName(name);
    }


}
