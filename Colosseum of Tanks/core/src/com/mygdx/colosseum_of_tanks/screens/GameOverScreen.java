package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.colosseum_of_tanks.TheGame;


public class GameOverScreen implements Screen {
    private final TheGame game;

    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;

    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;

    private Texture background1;
    private Texture background2;
    private int backgroundVelocity = 4;
    private int backgroundX = 0;

    private Label gameOverLabel;
    private Label winnerLabel;
    private Label messageLabel;
    private TextButton newButton;
    private TextButton backButton;

    public GameOverScreen(TheGame game, String message, String extraMessage) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        this.background1 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_blue_bg.png"));
        this.background2 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_blue_bg.png"));

        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        this.batch = new SpriteBatch();

        Table table = new Table();
        Table winnerLabelTable = new Table();

        this.gameOverLabel = new Label("Game Over", skin);
        this.winnerLabel = new Label(message, skin);
        this.messageLabel = new Label(extraMessage, skin);
        this.newButton = new TextButton("New", skin);
        this.backButton = new TextButton("Back", skin);

        table.setPosition(0, 0);
        table.center();
        table.setFillParent(true);
        table.add(gameOverLabel);
        table.row();
        table.add(newButton).padTop(40);
        table.row();
        table.add(backButton).padTop(10);

        winnerLabelTable.setPosition(0, 0);
        winnerLabelTable.bottom().center();
        winnerLabelTable.setFillParent(true);
        winnerLabelTable.add(winnerLabel).padTop(400).align(Align.center);
        winnerLabelTable.row();
        winnerLabelTable.add(messageLabel).padTop(10).align(Align.center);

        stage.addActor(table);
        stage.addActor(winnerLabelTable);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(background1, backgroundX, 0, WORLD_WIDTH, WORLD_HEIGHT);
        this.batch.draw(background2, backgroundX + WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);
        this.batch.end();

        backgroundX -= backgroundVelocity;
        if (backgroundX + WORLD_WIDTH == 0) {
            backgroundX = 0;
        }

        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();

        this.handleInput();

    }

    private void handleInput() {
        this.newButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new ChooseScreen(game));
            }
        });
        this.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StartScreen(game, TheGame.NEW_GAME));
            }
        });
    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
        skin.dispose();
        batch.dispose();
        background1.dispose();
        background2.dispose();
    }
}
