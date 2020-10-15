package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.gamemodel.GameModel;
import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.services.ITCPListener;
import com.backendboys.battlerace.view.screens.IScreen;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that handles inputs
 */
public class GameController implements InputProcessor {

    private final GameModel gameModel;
    private final BattleRace game;

    private final List<Integer> keysDown;

    private boolean usedPowerUp = false;

    ServerController serverController;

    /**
     * @param game Created GameModel and set GameScreen.
     */
    public GameController(BattleRace game) {
        gameModel = new GameModel();
        this.game = game;
        IScreen gameScreen = ScreenFactory.createGameScreen(this);
        game.setScreen(gameScreen);

        keysDown = new ArrayList<>();

        serverController = new ServerController(game, this);
        gameScreen.setServerController(serverController);
    }

    public GameController(BattleRace game, ServerController serverController) {
        gameModel = new GameModel();
        this.game = game;
        IScreen gameScreen = ScreenFactory.createGameScreen(this);
        gameScreen.setServerController(serverController);
        game.setScreen(gameScreen);

        keysDown = new ArrayList<>();
    }

    public void handleAddOpponent(OpponentPlayer opponent) {
        gameModel.addOpponent(opponent);
    }

    public void handleRemoveOpponent(OpponentPlayer opponent) {
        gameModel.removeOpponent(opponent);
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
                    toggleMenu();
                    break;
                case Input.Keys.SPACE:
                    if (!usedPowerUp) {
                        usedPowerUp = true;
                        gameModel.usePowerUp();
                    }
                default:
                    break;
            }
        }
    }

    private void toggleMenu() {
        game.startMenu();
    }

    @Override
    public boolean keyDown(int keycode) {
        keysDown.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
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
        switch (character) {
            case 'w':

                break;
            case 'a':

                break;
            case 's':

                break;
            case 'd':

                break;
            default:

                break;
        }
        return false;
    }

    public GameWorld getGameWorld() {
        return gameModel.getGameWorld();
    }

    public GameModel getGameModel() {
        return gameModel;
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

    public void onConnection() {
        System.out.println("create:gustav");
        //serverController.startServer("gustav");
        serverController.joinServer("Simon", "1401");
    }
}
