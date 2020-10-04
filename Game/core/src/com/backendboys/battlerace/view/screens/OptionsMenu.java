package com.backendboys.battlerace.view.screens;


import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The OptionMenu in our game. Is in view when it's set as the current screen for our application
 */
class OptionsMenu extends AbstractMenuScreen implements IScreen {

    private final SpriteBatch batch;
    private Stage stage;

    /**
     * Constructor
     *
     * @param menuController The controller is needed currently to check setting's
     */
    OptionsMenu(MenuController menuController) {
        super(menuController);

        batch = new SpriteBatch();
    }

    /**
     * Shows the OptionsMenu and calls the method that add the buttons to the menu
     */
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

        final ImageButton soundButton = new ImageButton(getButtonStyleFromName("Soundon"));
        if (getMenuController().isMusicPlaying()) {
            soundButton.setStyle(getButtonStyleFromName("Soundon"));
        } else {
            soundButton.setStyle(getButtonStyleFromName("Soundoff"));
        }

        ImageButton backToMainMenuButton = new ImageButton(getButtonStyleFromName("Back"));

        backToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toMainMenu();
            }
        });

        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getMenuController().isMusicPlaying()) {
                    getMenuController().playMenuMusic(false);
                    soundButton.setStyle(getButtonStyleFromName("Soundoff"));

                } else {
                    getMenuController().playMenuMusic(true);
                    soundButton.setStyle(getButtonStyleFromName("Soundon"));
                }


            }
        });

        optionsTable.add(soundButton).row();
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
