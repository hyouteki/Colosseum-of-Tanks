package com.mygdx.colosseum_of_tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TheGame extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton startButton;
    private TextButton quitButton;
	private Texture background;
	private SpriteBatch batch;
	private Sprite bg;
	private float WORLD_WIDTH;
	private float WORLD_HEIGHT;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
        stage = new Stage(new ScreenViewport());

		background = new Texture("images/background.png");

		batch = new SpriteBatch();
		bg = new Sprite(new Texture(Gdx.files.internal("images/background.png")));
		bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.right | Align.top);
		table.setPosition(0, Gdx.graphics.getHeight());

		startButton = new TextButton("Start game", skin);
		quitButton = new TextButton("Quit game", skin);

		table.padTop(30);
		table.padRight(30);
		table.add(startButton).padBottom(30);
		table.row();
		table.add(quitButton);

		stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		bg.draw(batch);
		batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
//		stage.getBatch().begin();
//		stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
    }
}
