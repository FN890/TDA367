package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The main menu in the game.
 * Includes properties of the objects in the menu such as the buttons.
 */
class MainMenu extends AbstractMenuScreen implements IScreen {

    private SpriteBatch batch;
    private Stage stage;

    MainMenu(MenuController menuController) {
        super(menuController);

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

        ImageButton singlePlayerBtn = new ImageButton(getButtonStyleFromName("Singleplayer"));
        ImageButton multiPlayerBtn = new ImageButton(getButtonStyleFromName("Multiplayer"));
        ImageButton optionsBtn = new ImageButton(getButtonStyleFromName("Options"));
        ImageButton exitBtn = new ImageButton(getButtonStyleFromName("Exit"));

        singlePlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toSinglePlayer();
            }
        });

        multiPlayerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toMultiPlayer();
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toOptions();
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().exit();
            }
        });

        menuTable.add(singlePlayerBtn).row();
        menuTable.add(multiPlayerBtn).row();
        menuTable.add(optionsBtn).row();
        menuTable.add(exitBtn).row();

        return menuTable;
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
