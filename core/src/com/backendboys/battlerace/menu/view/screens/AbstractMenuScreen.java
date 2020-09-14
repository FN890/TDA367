package com.backendboys.battlerace.menu.view.screens;

import com.backendboys.battlerace.menu.controller.MenuController;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractMenuScreen extends AbstractScreen {

    private final MenuController menuController;

    private final SpriteBatch batch;
    private final Texture background;

    private final TextureAtlas menuTextureAtlas;
    private final Map<String, Sprite> menuSprites = new HashMap<>();

    protected AbstractMenuScreen(MenuController menuController) {
        this.menuController = menuController;

        batch = new SpriteBatch();
        background = new Texture("bg-100.jpg");

        menuTextureAtlas = new TextureAtlas("menusprites.txt");
        loadSprites();
    }

    private TextureRegionDrawable getTextureRegionDrawable(String name) {
        return new TextureRegionDrawable(new TextureRegion(getMenuSprite(name)));
    }

    protected ImageButton.ImageButtonStyle getButtonStyleFromName(String name) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = getTextureRegionDrawable(name + "1");
        imageButtonStyle.imageDown = getTextureRegionDrawable(name + "2");
        imageButtonStyle.imageOver = getTextureRegionDrawable(name + "3");
        return imageButtonStyle;
    }

    private void loadSprites() {
        Array<TextureAtlas.AtlasRegion> regions = menuTextureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = menuTextureAtlas.createSprite(region.name);

            menuSprites.put(region.name, sprite);
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
        batch.draw(background, 0, 0, 1280, 800);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        batch.setProjectionMatrix(getCamera().combined);
    }

    @Override
    public void dispose() {
        super.dispose();
        menuTextureAtlas.dispose();
        background.dispose();
        menuSprites.clear();
    }

    protected Sprite getMenuSprite(String name) {
        return menuSprites.get(name);
    }

    protected MenuController getMenuController() {
        return menuController;
    }
}
