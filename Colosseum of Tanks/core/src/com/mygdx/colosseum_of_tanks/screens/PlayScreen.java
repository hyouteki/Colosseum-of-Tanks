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
import com.mygdx.colosseum_of_tanks.sprites.Drop;
import com.mygdx.colosseum_of_tanks.sprites.Missile;
import com.mygdx.colosseum_of_tanks.sprites.Tank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen, Serializable {
    private final TheGame game;

    private final float WORLD_WIDTH = 37 * 32;
    private final float WORLD_HEIGHT = 21 * 32;

    private final Viewport viewport;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private Tank tankL;
    private Tank tankR;
    private Missile missile;
    private Drop drop;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private HUD hud;

    private boolean turn = true;
    private boolean shoot = false;
    private boolean makeDrop = false;
    private boolean takenDrop = false;

    public PlayScreen(TheGame game) {
        this.game = game;

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

        this.game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

        this.game.batch.setProjectionMatrix(this.viewport.getCamera().combined);
        this.game.batch.begin();
        this.tankL.draw(this.game.batch);
        this.tankR.draw(this.game.batch);

        if (shoot) {
            this.missile.draw(this.game.batch);
        }

        if (makeDrop) {
            Gdx.app.log("DropState", "true");
            this.drop.draw(this.game.batch);
        }

        if (tankL.getHealth() == 0 || tankR.getHealth() == 0) {
            this.gameOver();
        }

        this.handleInput(delta);
        this.update(delta);

        this.game.batch.end();

    }

    private void gameOver() {
        dispose();
        Tank localTank = (tankL.getHealth() == 0) ? tankR : tankL;
        int player = (tankL.getHealth() == 0) ? 2 : 1;
        String message = String.format("Winner: Tank%d [%s]", player, localTank.getName());
        this.game.setScreen(new GameOverScreen(this.game, message, "Shoot to death !!"));
    }

    private void gameOver(Tank winner) {
        dispose();
        Tank localTank = (tankR == winner) ? tankR : tankL;
        int player = (tankR == winner) ? 2 : 1;
        String message = String.format("Winner: Tank%d [%s]", player, localTank.getName());
        this.game.setScreen(new GameOverScreen(this.game, message, "Thrown out of the screen"));
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Tank localTank = (this.turn) ? this.tankL : this.tankR;
            if (!localTank.flipped) {
                localTank.flip();
            }
            if (localTank.getBody().getLinearVelocity().x <= 2 && localTank.canMove()) {
                localTank.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
                        localTank.getBody().getWorldCenter(), true);
                localTank.decreaseFuel(TheGame.FUEL_DECREASE_FACTOR);
            }
            String motion = (localTank.flipped) ? "true" : "false";
            Gdx.app.log("motion", motion);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Tank localTank = (this.turn) ? this.tankL : this.tankR;
            if (localTank.flipped) {
                localTank.flip();
            }
            if (localTank.getBody().getLinearVelocity().x >= -2 && localTank.canMove()) {
                localTank.getBody().applyLinearImpulse(new Vector2(-0.1f, 0),
                        localTank.getBody().getWorldCenter(), true);
            }
            localTank.decreaseFuel(TheGame.FUEL_DECREASE_FACTOR);
            String motion = (localTank.flipped) ? "true" : "false";
            Gdx.app.log("motion", motion);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Tank localTank = (turn) ? this.tankL : this.tankR;
            localTank.resetFuel();
            if (!shoot) {
                if (localTank.canShoot()) {
                    missile = localTank.getMissile();
                    missile.setMotionPosition(localTank.getBody().getPosition().x, localTank.getBody().getPosition().y);
                    missile.setAngle(0);
                    missile.setFlipped(!localTank.flipped);
                    this.shoot = true;
                    localTank.decreaseMissileCount();
                    localTank.popMissile();
                } else {
                    switchTurn();
                }
            }
        }
    }

    private void update(float delta) {
        this.viewport.getCamera().update();
        this.world.step(1 / 60f, 6, 2);
        this.tankL.update(delta);
        this.tankR.update(delta);
        try {
            this.missile.update(delta);
            Tank secondTank = (turn) ? this.tankR : this.tankL;
            if (secondTank.getBoundingRectangle().overlaps(missile.getBoundingRectangle())) {
                this.shoot = false;
                Gdx.app.log("coalition", Integer.toString(secondTank.getHealth()));
                secondTank.decreaseHealth(this.missile.getDamage());
                missile.remove();
                missile = null;
                switchTurn();
            }
            if (missile.getPositionX() < 0 || missile.getPositionX() > WORLD_WIDTH / TheGame.PPM) {
                this.shoot = false;
                missile.remove();
                switchTurn();
                missile = null;
                Gdx.app.log("missileState", "missile left the screen");
            }
        } catch (Exception ignored) {
        }
        if (tankL.getBody().getPosition().x < 0 || tankL.getBody().getPosition().x > WORLD_WIDTH / TheGame.PPM) {
            this.gameOver(this.tankR);
        }
        if (tankR.getBody().getPosition().x < 0 || tankR.getBody().getPosition().x > WORLD_WIDTH / TheGame.PPM) {
            this.gameOver(this.tankL);
        }
        if (tankL.getMissileCount() == 0 || tankR.getMissileCount() == 0) {
            this.hud.update(this.turn);
            this.makeDrop();
        }
        try {
            this.drop.update(delta);
            Tank localTank = (turn) ? this.tankL : this.tankR;
            if (localTank.getBoundingRectangle().overlaps(drop.getBoundingRectangle())) {
                this.makeDrop = false;
                localTank.increaseMissileCount(1);
                localTank.addMissile(drop.releaseDrop());
                drop = null;
            }
        } catch (Exception ignored) {

        }
        try {
            if (drop.getBoundingRectangle().overlaps(missile.getBoundingRectangle())) {
                this.makeDrop = false;
                drop = null;
                this.shoot = false;
                missile.remove();
                missile = null;
                switchTurn();
            }
        } catch (Exception ignored) {

        }
        this.hud.update(this.turn);
    }

    private void makeDrop() {
        if (!this.makeDrop) {
            float xCoordinate = (float) (tankL.getBody().getPosition().x + Math.random() * (tankR.getBody().getPosition().x - tankL.getBody().getPosition().x));
            Gdx.app.log("Drop", Float.toString(xCoordinate));
            drop = new Drop(xCoordinate);
            this.makeDrop = true;
        }
    }

    private void debug() {
        box2DDebugRenderer.render(world, viewport.getCamera().combined);
    }

    private void switchTurn() {
        this.turn = !this.turn;
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
        box2DDebugRenderer.dispose();
        map.dispose();
    }

    public TheGame getGame() {
        return this.game;
    }
}
