package com.mygdx.colosseum_of_tanks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.colosseum_of_tanks.classes.Tank;

import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen {
    private final TheGame game;

    private final float WORLD_WIDTH;
    private final float WORLD_HEIGHT;

    private final Viewport viewport;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private SpriteBatch batch;

    private Tank tankL;
    private Tank tankR;
    private Sprite spriteL;
    private Sprite spriteR;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    public PlayScreen(TheGame game) {
        this.game = game;

        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();

        ArrayList<String> mapNameList = new ArrayList<String>();
        mapNameList.add("maps/grounded-night-fall.tmx");
//        mapNameList.add("maps/sole-valley.tmx");
        Collections.shuffle(mapNameList);
        this.map = new TmxMapLoader().load(mapNameList.get(0));
        this.renderer = new OrthogonalTiledMapRenderer(map);

        this.viewport = new StretchViewport(WORLD_WIDTH, 42 * 16, new OrthographicCamera());
        this.batch = new SpriteBatch();

        this.viewport.getCamera().position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        this.world = new World(new Vector2(0, 0), true);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        for (RectangleMapObject mapObject : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = mapObject.getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2,
                    rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }
    }

    public PlayScreen(TheGame game, Tank tankL, Tank tankR) {
        this(game);
        this.tankL = tankL;
        this.tankR = tankR;
        this.tankL.setSprite(20, 20, 50, 50);
        this.tankR.setSprite(40, 40, 50, 50);
        this.spriteL = this.tankL.getSprite();
        this.spriteR = this.tankR.getSprite();
        this.spriteL.setPosition(10, 10);
        this.spriteL.setRotation(45);
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

        box2DDebugRenderer.render(world, viewport.getCamera().combined);

//        batch.begin();
////        spriteL.draw(batch);
//        batch.draw(tankL.getTexture(), 80, 125, 80, 80);
//        batch.end();

        this.handleInput(delta);
        this.update(delta);

    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.T)) {

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (this.viewport.getCamera().position.x < 870) {
                this.viewport.getCamera().position.x += 400 * delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (this.viewport.getCamera().position.x > 328) {
                this.viewport.getCamera().position.x -= 400 * delta;
            }
        }
    }

    private void update(float delta) {
        this.viewport.getCamera().update();
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
