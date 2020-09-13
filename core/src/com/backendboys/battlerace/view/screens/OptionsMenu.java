package com.backendboys.battlerace.view.screens;


import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

class OptionsMenu extends AbstractMenuScreen implements IScreen {

    private final SpriteBatch batch;
    private Stage stage;

    OptionsMenu(MenuController menuController) {
        super(menuController);

        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        super.show();
        stage = new Stage(getViewport(), batch);

        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        // TODO: Switch to ImageButtons and fix update sprites with more images.
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();

        TextButton soundOnButton = new TextButton("Sound ON!", buttonStyle);
        TextButton soundOffButton = new TextButton("Sound OFF!", buttonStyle);
        TextButton backToMainMenuButton = new TextButton("Back to main menu!", buttonStyle);

        backToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toMainMenu();
            }
        });

        soundOffButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().playMenuMusic(false);
            }
        });

        soundOnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().playMenuMusic(true);
            }
        });

        mainTable.add(soundOnButton).row();
        mainTable.add(soundOffButton).row();
        mainTable.add(backToMainMenuButton).row();


        stage.addActor(mainTable);
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
    public void dispose() {
        super.dispose();
    }


}
