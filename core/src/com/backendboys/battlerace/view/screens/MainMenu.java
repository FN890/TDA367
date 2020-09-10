package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AbstractScreen {

    private final MenuController menuController;

    private SpriteBatch batch;
    private Stage stage;
    private Table menuTable;
    private TextureAtlas textureAtlas;

    private Texture backgroundTexture;

    private Map<String, Sprite> sprites = new HashMap<>();

    public MainMenu(MenuController menuController) {
        this.menuController = menuController;

        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        super.show();

        backgroundTexture = new Texture("bg-100.jpg");

        stage = new Stage(getViewport(), batch);

        Gdx.input.setInputProcessor(stage);

        menuTable = createButtonsTable();
        stage.addActor(menuTable);
    }

    private Table createButtonsTable() {

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.center();



        //TODO: Fix button images

        TextureRegion region = new TextureRegion(new Texture("Singleplayer1.png"));
        TextureRegionDrawable drawable = new TextureRegionDrawable(region);

        ImageButton singlePlayerBtn = new ImageButton(drawable);
        ImageButton multiPlayerBtn = new ImageButton(drawable);
        ImageButton optionsBtn = new ImageButton(drawable);
        ImageButton exitBtn = new ImageButton(drawable);

        singlePlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuController.playPressed();
            }
        });

        multiPlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuController.multiPlayerPressed();
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuController.optionsPressed();
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuController.exitPressed();
            }
        });

        menuTable.add(singlePlayerBtn).row();
        menuTable.add(multiPlayerBtn).row();
        menuTable.add(optionsBtn).row();
        menuTable.add(exitBtn).row();

        return menuTable;
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
        batch.draw(backgroundTexture, 0, 0, getViewport().getWorldWidth(), getViewport().getWorldHeight());
        batch.end();

        stage.act();
        stage.draw();
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
