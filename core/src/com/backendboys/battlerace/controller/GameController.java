package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.model.GameModel;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameController implements InputProcessor {

    //Get inputs from views and do changes in model

    private GameModel gameModel;

    public GameController() {
        gameModel = new GameModel();
    }

    @Override
    public boolean keyDown(int keycode) {
        gameModel.keyDown(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        gameModel.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        gameModel.keyTyped(character);
        return false;
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
