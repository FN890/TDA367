package com.backendboys.battlerace.view.screens;


import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.view.IRender;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

public class OptionsMenu extends AbstractScreen implements Screen, IRender {

    private SpriteBatch batch;
    private BattleRace battleRace;
    private Stage stage;
    private Map <String, Sprite> spriteMap = new HashMap<>();
    private TextureAtlas textureAtlas;
    public OptionsMenu(BattleRace battleRace) {
        this.battleRace = battleRace;
        batch = new SpriteBatch();
        stage = new Stage(super.getViewport(),batch);
        textureAtlas = new TextureAtlas();
        addSprites();
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        for(TextureAtlas.AtlasRegion region : regions){
            Sprite sprite = textureAtlas.createSprite(region.name);
            spriteMap.put(region.name,sprite);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        batch.setProjectionMatrix(super.getCamera().combined);
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
    public void init() {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}
