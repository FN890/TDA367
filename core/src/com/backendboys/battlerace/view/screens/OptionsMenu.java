package com.backendboys.battlerace.view.screens;


import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
        stage.addActor(createOptionsTable());

        Gdx.input.setInputProcessor(stage);

    }

    private Table createOptionsTable() {
        Table optionsTable = new Table();
        optionsTable.setFillParent(true);
        optionsTable.center();


        // TODO:add images for sound on/off.
        ImageButton soundOnButton = new ImageButton(getButtonStyleFromName("Back"));
        ImageButton soundOffButton = new ImageButton(getButtonStyleFromName("Back"));
        ImageButton backToMainMenuButton = new ImageButton(getButtonStyleFromName("Back"));

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

        optionsTable.add(soundOnButton).row();
        optionsTable.add(soundOffButton).row();
        optionsTable.add(backToMainMenuButton).row();

        return optionsTable;
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
