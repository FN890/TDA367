package com.backendboys.battlerace.view.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AbstractScreen implements Screen {

    private SpriteBatch batch;
    private Stage stage;
    private TextureAtlas textureAtlas;

    private Map<String, Sprite> sprites = new HashMap<>();

    @Override
    public void show() {
        super.show();
        //textureAtlas = new TextureAtlas("menusprites.txt");
        batch = new SpriteBatch();
        stage = new Stage(getViewport(), batch);

        //addSprites();
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        batch.setProjectionMatrix(getCamera().combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        sprites.clear();
    }
}
