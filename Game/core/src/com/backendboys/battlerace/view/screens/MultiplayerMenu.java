package com.backendboys.battlerace.view.screens;


import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.controller.ServerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * The MultiplayerMenu, here you can chose between create game or join game.
 */
class MultiplayerMenu extends AbstractMenuScreen implements IScreen {

    private final SpriteBatch batch;
    private Stage stage;

    private TextField inputPlayerName;
    private TextField inputGameId;

    MultiplayerMenu(MenuController menuController) {
        super(menuController);

        batch = new SpriteBatch();
    }

    /**
     * Shows the MultiplayerMenu and calls the method that add the buttons to the menu
     */
    @Override
    public void show() {
        super.show();

        stage = new Stage(getViewport(), batch);
        stage.addActor(createMultiplayerTable());

        Gdx.input.setInputProcessor(stage);

    }

    private Table createMultiplayerTable() {
        Table multiplayerTable = new Table();
        multiplayerTable.setFillParent(true);
        multiplayerTable.center();

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        Label lblPlayerName = new Label("Player name: ", uiSkin);
        lblPlayerName.setFontScale(1.5f);

        Label lblGameId = new Label("Game id: ", uiSkin);
        lblGameId.setFontScale(1.5f);

        Label vSpace = new Label("", uiSkin);

        inputPlayerName = new TextField("", uiSkin);
        inputGameId = new TextField("", uiSkin);

        final ImageButton btnJoinGame = new ImageButton(getButtonStyleFromName("Exit"));
        final ImageButton btnCreateGame = new ImageButton(getButtonStyleFromName("Options"));
        final ImageButton btnBack = new ImageButton(getButtonStyleFromName("Back"));

        btnJoinGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!inputPlayerName.getText().isEmpty() && !inputGameId.getText().isEmpty()) {

                    getMenuController().getGameController().setGameScreen();
                    //Set name and id so we can join game when we are connected.
                    getMenuController().getServerController().setNameAndId(inputPlayerName.getText(), inputGameId.getText());

                    //Starts TCPClient and UDPClient
                    getMenuController().getServerController().connect();
                }
            }
        });

        btnCreateGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!inputPlayerName.getText().isEmpty()) {

                    getMenuController().getGameController().setGameScreen();
                    //Set name and id so we can create game when we are connected.
                    getMenuController().getServerController().setNameAndId(inputPlayerName.getText(), "");

                    //Starts TCPClient and UDPClient
                    getMenuController().getServerController().connect();
                }
            }
        });

        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMenuController().toMainMenu();
            }
        });

        multiplayerTable.add(lblPlayerName).align(Align.left).row();
        multiplayerTable.add(inputPlayerName).width(200).row();

        multiplayerTable.add(vSpace).row();

        multiplayerTable.add(lblGameId).align(Align.left).row();
        multiplayerTable.add(inputGameId).width(200).row();

        multiplayerTable.add(vSpace).row();

        multiplayerTable.add(btnJoinGame).row();
        multiplayerTable.add(btnCreateGame).row();
        multiplayerTable.add(btnBack).row();

        multiplayerTable.pack();

        return multiplayerTable;
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
        batch.dispose();
        stage.dispose();
    }

}
