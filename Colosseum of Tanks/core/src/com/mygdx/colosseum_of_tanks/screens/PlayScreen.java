package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.colosseum_of_tanks.TheGame;
import com.mygdx.colosseum_of_tanks.scenes.HUD;
import com.mygdx.colosseum_of_tanks.sprites.Tank;

import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen {
    private final TheGame game;

    private final float WORLD_WIDTH;
    private final float WORLD_HEIGHT = 21 * 32;

    private final Viewport viewport;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private Tank tankL;
    private Tank tankR;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private HUD hud;

    private boolean turn = true;

    public PlayScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();

        ArrayList<String> mapNameList = new ArrayList<String>();
        mapNameList.add("maps/grounded-night-fall.tmx");
//        mapNameList.add("maps/sole-valley.tmx");
        Collections.shuffle(mapNameList);
        this.map = new TmxMapLoader().load(mapNameList.get(0));
        this.renderer = new OrthogonalTiledMapRenderer(map, 1 / TheGame.PPM);

        this.viewport = new StretchViewport(this.WORLD_WIDTH / TheGame.PPM, this.WORLD_HEIGHT / TheGame.PPM, new OrthographicCamera());

        this.viewport.getCamera().position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        this.world = new World(new Vector2(0, -10), true);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        for (RectangleMapObject mapObject : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = mapObject.getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / TheGame.PPM,
                    (rectangle.getY() + rectangle.getHeight() / 2) / TheGame.PPM);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rectangle.getWidth() / 2 / TheGame.PPM, rectangle.getHeight() / 2 / TheGame.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }
    }

    public PlayScreen(TheGame game, Tank tankL, Tank tankR) {
        this(game);
        this.tankL = tankL;
        this.tankR = tankR;
        this.tankL.setWorld(world);
        this.tankR.setWorld(world);
        this.tankR.setFlip(true, false);
        this.tankL.defineBody();
        this.tankR.defineBody();
        this.hud = new HUD(this.game.batch, tankL, tankR);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView((OrthographicCamera) this.viewport.getCamera());
        renderer.render();

//        debug();

        this.game.batch.setProjectionMatrix(this.viewport.getCamera().combined);
        this.game.batch.begin();
        this.tankL.draw(this.game.batch);
        this.tankR.draw(this.game.batch);
        this.game.batch.end();

        this.game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

        this.handleInput(delta);
        this.update(delta);

    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (this.viewport.getCamera().position.x < WORLD_WIDTH * 1.333f / TheGame.PPM) {
                this.viewport.getCamera().position.x += 10 * delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (this.viewport.getCamera().position.x > WORLD_WIDTH / TheGame.PPM / 2) {
                this.viewport.getCamera().position.x -= 10 * delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Tank localTank = (this.turn)? this.tankL: this.tankR;
            if (localTank.getBody().getLinearVelocity().x <= 2 && localTank.canMove()) {
                localTank.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
                        localTank.getBody().getWorldCenter(), true);
                localTank.decreaseFuel(TheGame.FUEL_DECREASE_FACTOR);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Tank localTank = (this.turn)? this.tankL: this.tankR;
            if (localTank.getBody().getLinearVelocity().x >= -2 && localTank.canMove()) {
                localTank.getBody().applyLinearImpulse(new Vector2(-0.1f, 0),
                        localTank.getBody().getWorldCenter(), true);
            }
            localTank.decreaseFuel(TheGame.FUEL_DECREASE_FACTOR);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Tank localTank = (turn)? this.tankL: this.tankR;
            localTank.decreaseMissileCount();
            localTank.resetFuel();
            this.turn = !this.turn;
            Gdx.app.log("dkdk", Integer.toString(this.tankR.getFuel()));
        }
    }

    private void update(float delta) {
        this.viewport.getCamera().update();
        this.world.step(1 / 60f, 6, 2);
        this.tankL.update(delta);
        this.tankR.update(delta);
        this.hud.update(this.turn);
    }

    private void debug() {
        box2DDebugRenderer.render(world, viewport.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.getCamera().update();
        this.viewport.update(width, height);
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
        world.dispose();
        box2DDebugRenderer.dispose();
        map.dispose();
    }

    public TheGame getGame() {
        return this.game;
    }
}
