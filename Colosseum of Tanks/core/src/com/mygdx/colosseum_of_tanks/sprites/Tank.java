package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.colosseum_of_tanks.TheGame;

import java.util.ArrayList;

public class Tank extends Sprite {
    public boolean flipped;
    public Texture tankImage;
    public Texture tankImageFlipped;
    public Texture tankCard;
    public Texture tankCardFlipped;
    public static final int DEFAULT_TANK1_START_POSITION_X = 64;
    public static final int DEFAULT_TANK1_START_POSITION_Y = 192;
    public static final int DEFAULT_TANK2_START_POSITION_X = 1120;
    public static final int DEFAULT_TANK2_START_POSITION_Y = 192;
    protected int health;
    protected int positionX;
    protected int positionY;
    protected ArrayList<Missile> missiles;
    protected ArrayList<Missile> availableMissiles;
    protected int missileCount;
    protected int fuel;
    protected int power;
    protected int angle;
    protected Missile currentMissile = null;
    protected World world;
    protected Body body;

    public Tank() {
        this.health = 100;
        this.missiles = new ArrayList<>();
        this.availableMissiles = new ArrayList<>();
        this.missileCount = 6;
        this.fuel = 100;
        this.power = 100;
        this.angle = 45;
    }

    public Tank(int positionX, int positionY, boolean flipped) {
        this.health = 100;
        this.missiles = new ArrayList<>();
        this.availableMissiles = new ArrayList<>();
        this.missileCount = 6;
        this.fuel = 100;
        this.power = 100;
        this.angle = 45;
        this.positionX = positionX;
        this.positionY = positionY;
        this.flipped = flipped;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(positionX / TheGame.PPM, positionY / TheGame.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5 / TheGame.PPM);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
        setBounds(0, 0, 32 / TheGame.PPM, 32 / TheGame.PPM);
        if (flipped) {
            setRegion(tankImage);
        } else {
            setRegion(tankImageFlipped);
        }
    }

    public Body getBody() {
        return this.body;
    }

    public void update(float delta) {
        setPosition(this.body.getPosition().x - this.getWidth() / 2, this.body.getPosition().y - this.getHeight() / 4);
    }

    public void set_available_missiles(ArrayList<Missile> missiles) {
        this.availableMissiles.addAll(missiles);
    }

    public int get_available_missiles_count() {
        return this.availableMissiles.size();
    }

    public void move_forward(int factor) {
        // TO-DO
    }

    public void move_backward(int factor) {
        // TO-DO
    }

    public void aim_missile() {
        // Missile missile = this.current_missile;
        // TO-DO
    }

    public void collectDrop(Drop drop) {
        this.missiles.addAll(drop.get_missiles());
    }

    public int getHealth() {
        return this.health;
    }

    public void decreaseHealth(int factor) {
        this.health -= factor;
    }

    public void increaseHealth(int factor) {
        this.health += factor;
    }

    public int[] getPosition() {
        return new int[]{positionX, positionY};
    }

    public void decreaseMissileCount() {
        this.missileCount -= 1;
    }

    public int getMissileCount() {
        return this.missileCount;
    }

    public ArrayList<Missile> getMissiles() {
        return this.missiles;
    }

    public void decreaseFuel(int factor) {
        this.fuel -= factor;
        if (this.fuel < 0) {
            this.fuel = 0;
        }
    }

    public int getFuel() {
        return this.fuel;
    }

    public boolean canMove() {
        return this.fuel > 0;
    }

    public void resetFuel() {
        this.fuel = 100;
    }

    public void knockBack() {
        // TO-DO
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void fireMissile() {

    }

    public Missile getCurrentMissile() {
        return this.currentMissile;
    }

    public void setCurrentMissile(int index) {
        this.currentMissile = availableMissiles.get(index);
    }

    public void setCurrentMissile(Missile missile) {
        this.currentMissile = missile;
    }


}