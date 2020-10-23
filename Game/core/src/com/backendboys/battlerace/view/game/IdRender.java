package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class IdRender extends AbstractRender {


    private final Label lblGameId;
    private final GameController gameController;

    public IdRender(OrthographicCamera orthographicCamera, GameController gameController) {
        super(orthographicCamera);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("decima.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 10;
        BitmapFont defaultFont = generator.generateFont(parameter);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = defaultFont;
        style.fontColor = Color.RED;
        generator.dispose();

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        this.gameController = gameController;
        lblGameId = new Label("Game Id: 1400", uiSkin);
        lblGameId.setStyle(style);
        lblGameId.setFontScale(1f);

    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.setProjectionMatrix(getProjectionMatrix());

        if (gameController.getServerController() != null) {
            if (gameController.getServerController().isConnected()) {
                lblGameId.setPosition((getCameraPosition().x - getViewportWidth() / 2.01f), getCameraPosition().y + getViewportHeight() / 2.25f);
                lblGameId.setText("Game id: " + gameController.getServerController().getGameId());
                lblGameId.draw(batch, 1);
            }
        }

        batch.end();
    }

    public void dispose() {
    }

}
