package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.colosseum_of_tanks.TheGame;
import com.mygdx.colosseum_of_tanks.classes.*;

import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen {
    private final TheGame game;

    private final float WORLD_WIDTH;
    private final float WORLD_HEIGHT;

    private final OrthographicCamera cam;
    private final Viewport port;

    private final TiledMap map;
    private ArrayList<String> mapNameList = new ArrayList<String>();
    private final OrthogonalTiledMapRenderer renderer;

    private Tank tankL;
    private Tank tankR;

    public PlayScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        mapNameList.add("maps/night-fall.tmx");
        mapNameList.add("maps/sole-valley.tmx");
        Collections.shuffle(mapNameList);
        this.map = new TmxMapLoader().load(mapNameList.get(0));
        this.renderer = new OrthogonalTiledMapRenderer(map);

        this.cam = new OrthographicCamera();
        this.port = new StretchViewport(WORLD_WIDTH, 42*16, cam);

        this.cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
    }

    public PlayScreen(TheGame game, Tank tankL, Tank tankR) {
        this(game);
        this.tankL = tankL;
        this.tankR = tankR;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        renderer.setView(cam);
        renderer.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (this.cam.position.x < 870) {
                this.cam.position.x += 400 * delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (this.cam.position.x > 328) {
                this.cam.position.x -= 400 * delta;
            }
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
