package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.GameModel;
import com.backendboys.battlerace.model.world.GameWorld;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController implements InputProcessor {

    private final GameModel gameModel;
    private final BattleRace game;

    private final List<Integer> keysDown;

    /**
     * @param game Created GameModel and set GameScreen.
     */
    public GameController(BattleRace game) {
        gameModel = new GameModel();
        this.game = game;
        game.setScreen(ScreenFactory.createGameScreen(this));

        keysDown = new ArrayList<>();
    }

    public void gameRendered() {
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
        keysDown.removeAll(Arrays.asList(keycode));
        return true;
    }

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
}
