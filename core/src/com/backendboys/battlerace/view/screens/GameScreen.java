package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.model.IModelListener;
import com.backendboys.battlerace.model.world.GameWorld;

public class GameScreen extends AbstractScreen implements IModelListener {

    private GameWorld gameWorld;

    public GameScreen() {
        gameWorld = new GameWorld();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameWorld.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameWorld.resize(width, height);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }

    @Override
    public void update() {
        gameWorld.render();
    }
}
