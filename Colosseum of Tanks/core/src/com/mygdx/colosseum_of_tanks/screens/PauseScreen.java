package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.colosseum_of_tanks.TheGame;

public class PauseScreen implements Screen {
    private final TheGame game;
    private GameScreen gameScreen;

    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;

    private Stage stage;
    private Skin skin;
    private Table table;
    private SpriteBatch batch;

    private Texture background1;
    private Texture background2;
    private int backgroundVelocity = 4;
    private int backgroundX = 0;

    private TextButton resumeButton;
    private TextButton restartButton;
    private TextButton saveButton;
    private TextButton exitButton;

    public PauseScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        this.table = new Table();
        this.batch = new SpriteBatch();

        this.background1 = new Texture(Gdx.files.internal("images/tank_stars/tank_stars_green_bg.png"));
        this.background2 = new Texture(Gdx.files.internal("images/tank_stars/tank_stars_green_bg.png"));

        this.resumeButton = new TextButton("Resume game", skin);
        this.restartButton = new TextButton("Restart game", skin);
        this.saveButton = new TextButton("Save game state", skin);
        this.exitButton = new TextButton("Exit to main menu", skin);

        table.setPosition(0, 0);
        table.setFillParent(true);
        table.center();
        table.add(resumeButton);
        table.row();
        table.add(restartButton).padTop(10);
        table.row();
        table.add(saveButton).padTop(10);
        table.row();
        table.add(exitButton).padTop(10);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public PauseScreen(GameScreen gameScreen) {
        this(gameScreen.getGame());
        this.gameScreen = gameScreen;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background1, backgroundX, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background2, backgroundX + WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();

        backgroundX -= backgroundVelocity;
        if (backgroundX + WORLD_WIDTH == 0) {
            backgroundX = 0;
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(gameScreen);
            }
        });

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameScreen(game));
            }
        });

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StartScreen(game, TheGame.SAVE_GAME));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StartScreen(game, TheGame.NEW_GAME));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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