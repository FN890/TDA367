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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AbstractMenuScreen {

    private final MenuController menuController;

    private SpriteBatch batch;
    private Stage stage;

    public MainMenu(MenuController menuController) {
        this.menuController = menuController;

        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        super.show();

        stage = new Stage(getViewport(), batch);
        stage.addActor(createButtonsTable());

        Gdx.input.setInputProcessor(stage);
    }

    private Table createButtonsTable() {

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.center();

        ImageButton singlePlayerBtn = new ImageButton(getMenuButtonStyleForName("Singleplayer"));
        ImageButton multiPlayerBtn = new ImageButton(getMenuButtonStyleForName("Multiplayer"));
        ImageButton optionsBtn = new ImageButton(getMenuButtonStyleForName("Options"));
        ImageButton exitBtn = new ImageButton(getMenuButtonStyleForName("Exit"));

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

    private TextureRegionDrawable getTextureRegionDrawable(String name) {
        return new TextureRegionDrawable(new TextureRegion(getMenuSprite(name)));
    }

    private ImageButton.ImageButtonStyle getMenuButtonStyleForName(String name) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = getTextureRegionDrawable(name + "1");
        style.imageOver = getTextureRegionDrawable(name + "2");
        style.imageDown = getTextureRegionDrawable(name + "3");

        return style;
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
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
        super.dispose();
    }
}
