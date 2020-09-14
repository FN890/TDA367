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
        //stage.addActor(CreateOptionsTable());

        Gdx.input.setInputProcessor(stage);

    }

    private TextureRegionDrawable getTextureRegionDrawable(String name) {
        return new TextureRegionDrawable(new TextureRegion(getMenuSprite(name)));
    }
    private ImageButton.ImageButtonStyle getOptionsButtonStyleFromName(String name){
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = getTextureRegionDrawable(name+"1");
        imageButtonStyle.imageDown = getTextureRegionDrawable(name+"2");
        imageButtonStyle.imageOver = getTextureRegionDrawable(name+"3");
        return imageButtonStyle;
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
