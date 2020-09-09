package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AbstractScreen implements Screen {

    private final MenuController menuController;
    private SpriteBatch batch;
    private Stage stage;
    private TextureAtlas textureAtlas;

    private Map<String, Sprite> sprites = new HashMap<>();

    public MainMenu(MenuController menuController) {
        this.menuController = menuController;

        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        super.show();
        stage = new Stage(getViewport(), batch);

        Gdx.input.setInputProcessor(stage);

        // TODO: Set Table settings.
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        //TODO: Create TextureAtlas for Buttons and asign to a skin.
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();


        TextButton singlePlayerBtn = new TextButton("Singleplayer", buttonStyle);
        TextButton multiPlayerBtn = new TextButton("Multiplayer", buttonStyle);
        TextButton optionsBtn = new TextButton("Options", buttonStyle);
        TextButton exitBtn = new TextButton("Exit", buttonStyle);

        singlePlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuController.playPressed();
            }
        });

        multiPlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                multiPlayerPressed();
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
                exitPressed();
            }
        });

        mainTable.add(singlePlayerBtn).row();
        mainTable.add(multiPlayerBtn).row();
        mainTable.add(optionsBtn).row();
        mainTable.add(exitBtn).row();

        stage.addActor(mainTable);
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    private void singlePlayerPressed() {


    }

    private void multiPlayerPressed() {


    }

    private void optionsPressed() {


    }

    private void exitPressed() {


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();

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
