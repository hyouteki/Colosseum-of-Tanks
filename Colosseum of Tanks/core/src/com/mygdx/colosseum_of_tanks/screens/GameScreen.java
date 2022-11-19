package com.mygdx.colosseum_of_tanks.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.colosseum_of_tanks.TheGame;

public class GameScreen implements Screen {
    private TheGame game;

    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;

    private final OrthographicCamera cam;
    private final Viewport port;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public GameScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        this.cam = new OrthographicCamera();
        this.port = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cam);

        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("maps/grassy_highland.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map, 2);
        this.cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        renderer.setView(cam);
        renderer.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.cam.position.x += 300 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.cam.position.x -= 300 * delta;
        }
    }

    @Override
    public void resize(int width, int height) {
        this.cam.update();
        this.port.update(width, height);
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
        renderer.dispose();
    }

    public TheGame getGame() {
        return this.game;
    }
}
