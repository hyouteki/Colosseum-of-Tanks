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


public class ChooseGameScreen implements Screen {
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

    private TextButton backButton;
    private TextButton slot1;
    private TextButton slot2;
    private TextButton slot3;

    public ChooseGameScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        this.background1 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_pink_bg.jpeg"));
        this.background2 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_pink_bg.jpeg"));

        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        this.batch = new SpriteBatch();

        Table table = new Table();
        Table tableButtons = new Table();

        this.backButton = new TextButton("Back", skin);
        this.slot1 = new TextButton("Slot-1", skin);
        this.slot2 = new TextButton("Slot-2", skin);
        this.slot3 = new TextButton("Slot-3", skin);

        if (TheGame.slot1 == null) {
            this.slot1.setText("Slot-1 [Empty]");
        } else {
            this.slot1.setText("Slot-1 [Filled]");
        }
        if (TheGame.slot2 == null) {
            this.slot2.setText("Slot-2 [Empty]");
        } else {
            this.slot2.setText("Slot-2 [Filled]");
        }
        if (TheGame.slot3 == null) {
            this.slot3.setText("Slot-3 [Empty]");
        } else {
            this.slot3.setText("Slot-3 [Filled]");
        }

        table.setPosition(0, 0);
        table.setFillParent(true);
        table.top().right();
        table.add(backButton).padTop(10).padRight(10);

        tableButtons.setPosition(0, 0);
        tableButtons.center();
        tableButtons.setFillParent(true);
        tableButtons.add(slot1);
        tableButtons.row();
        tableButtons.add(slot2).padTop(10);
        tableButtons.row();
        tableButtons.add(slot3).padTop(10);

        stage.addActor(table);
        stage.addActor(tableButtons);
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
        this.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StartScreen(game, TheGame.NEW_GAME));
            }
        });
        this.slot1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (TheGame.slot1 != null) {
                    dispose();
                    game.setScreen(TheGame.slot1);
                }
            }
        });
        this.slot2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (TheGame.slot2 != null) {
                    dispose();
                    game.setScreen(TheGame.slot2);
                }
            }
        });
        this.slot3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (TheGame.slot3 != null) {
                    dispose();
                    game.setScreen(TheGame.slot3);
                }
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
