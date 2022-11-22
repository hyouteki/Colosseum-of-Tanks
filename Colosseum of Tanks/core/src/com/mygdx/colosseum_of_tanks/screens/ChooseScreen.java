package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.colosseum_of_tanks.TheGame;
import com.mygdx.colosseum_of_tanks.classes.*;

public class ChooseScreen implements Screen {
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

    private TextButton nextButton;
    private TextButton backButton;

    private Image atomicTankImage;
    private Image heliosTankImage;
    private Image mark1TankImage;

    private TextButton chooseL1;
    private TextButton chooseL2;
    private TextButton chooseL3;
    private TextButton chooseR1;
    private TextButton chooseR2;
    private TextButton chooseR3;

    private Tank tankL = null;
    private Tank tankR = null;

    public ChooseScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        Table table = new Table();
        this.batch = new SpriteBatch();

        this.background1 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_red_bg.jpeg"));
        this.background2 = new Texture(Gdx.files.internal("images/tank-stars/tank_stars_red_bg.jpeg"));

        this.nextButton = new TextButton("Next", skin);
        this.backButton = new TextButton("Back", skin);

        this.atomicTankImage = new Image(new TextureRegionDrawable(new Texture("images/tanks/atomic.png")));
        this.heliosTankImage = new Image(new TextureRegionDrawable(new Texture("images/tanks/helios.png")));
        this.mark1TankImage = new Image(new TextureRegionDrawable(new Texture("images/tanks/mark_1.png")));

        this.chooseL1 = new TextButton("Choose", skin, "toggle");
        this.chooseL2 = new TextButton("Choose", skin, "toggle");
        this.chooseL3 = new TextButton("Choose", skin, "toggle");
        this.chooseR1 = new TextButton("Choose", skin, "toggle");
        this.chooseR2 = new TextButton("Choose", skin, "toggle");
        this.chooseR3 = new TextButton("Choose", skin, "toggle");

        Table tableButtons = new Table();

        table.setPosition(0, 0);
        table.setFillParent(true);
        table.top().right();
        table.add(nextButton).padTop(10).padRight(10);
        table.row();
        table.add(backButton).padTop(10).padRight(10);

        tableButtons.setPosition(0, 0);
        tableButtons.center();
        tableButtons.setFillParent(true);
        tableButtons.add(chooseL1);
        tableButtons.add(chooseL2).padLeft(10);
        tableButtons.add(chooseL3);
        tableButtons.row();
        tableButtons.add(atomicTankImage).width(129).height(75);
        tableButtons.add(heliosTankImage).width(175).height(175).padLeft(10);
        tableButtons.add(mark1TankImage).width(150).height(85);
        tableButtons.row();
        tableButtons.add(chooseR1);
        tableButtons.add(chooseR2).padLeft(10);
        tableButtons.add(chooseR3);

        stage.addActor(table);
        stage.addActor(tableButtons);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background1, backgroundX, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background2, backgroundX + WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();

        backgroundX -= backgroundVelocity;
        if (backgroundX + WORLD_WIDTH == 0) {
            backgroundX = 0;
        }

        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (chooseL1.isChecked() || chooseL2.isChecked() || chooseL3.isChecked() && chooseR1.isChecked() || chooseR2.isChecked() || chooseR3.isChecked()) {
                    if (tankL != null && tankR != null) {
                        dispose();
                        game.setScreen(new PlayScreen(game, tankL, tankR));
                    }
                }
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StartScreen(game, TheGame.NEW_GAME));
            }
        });

        chooseL1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankL = new AtomicTank();
                if (chooseL2.isChecked()) {
                    chooseL2.toggle();
                }
                if (chooseL3.isChecked()) {
                    chooseL3.toggle();
                }
            }
        });

        chooseL2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankL = new HeliosTank();
                if (chooseL1.isChecked()) {
                    chooseL1.toggle();
                }
                if (chooseL3.isChecked()) {
                    chooseL3.toggle();
                }
            }
        });

        chooseL3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankL = new Mark1Tank();
                if (chooseL1.isChecked()) {
                    chooseL1.toggle();
                }
                if (chooseL2.isChecked()) {
                    chooseL2.toggle();
                }
            }
        });

        chooseR1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankR = new AtomicTank();
                if (chooseR2.isChecked()) {
                    chooseR2.toggle();
                }
                if (chooseR3.isChecked()) {
                    chooseR3.toggle();
                }
            }
        });

        chooseR2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankR = new HeliosTank();
                if (chooseR1.isChecked()) {
                    chooseR1.toggle();
                }
                if (chooseR3.isChecked()) {
                    chooseR3.toggle();
                }
            }
        });

        chooseR3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tankR = new Mark1Tank();
                if (chooseR1.isChecked()) {
                    chooseR1.toggle();
                }
                if (chooseR2.isChecked()) {
                    chooseR2.toggle();
                }
            }
        });

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
