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
    public Texture currentImage;
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
    protected int missileCount;
    protected int fuel;
    protected int power;
    protected int angle;
    protected String name;
    protected Missile currentMissile = null;
    protected World world;
    protected Body body;

    public Tank(int positionX, int positionY, boolean flipped) {
        this.health = 100;
        this.missiles = new ArrayList<>();
        this.missileCount = 6;
        this.fuel = 100;
        this.power = 100;
        this.angle = 45;
        this.positionX = positionX;
        this.positionY = positionY;
        this.flipped = flipped;
        for (int i = 0; i < 6; i++) {
            missiles.add(new Grenade(0, 0, 0, !flipped));
        }
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
        circleShape.setRadius(7 / TheGame.PPM);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
        setBounds(0, 0, 32 * 2 / TheGame.PPM, 32 / TheGame.PPM);
        if (flipped) {
            this.currentImage = tankImage;
            setRegion(tankImage);
        } else {
            this.currentImage = tankImageFlipped;
            setRegion(tankImageFlipped);
        }
    }

    public Body getBody() {
        return this.body;
    }

    public void flip() {
        this.flipped = !this.flipped;
        if (this.currentImage == this.tankImage) {
            this.currentImage = this.tankImageFlipped;
        } else if (this.currentImage == this.tankImageFlipped) {
            this.currentImage = this.tankImage;
        }
        setRegion(this.currentImage);
    }

    public void update(float delta) {
        setPosition(this.body.getPosition().x - this.getWidth() / 2, this.body.getPosition().y - this.getHeight() / 4);
    }


    public int getHealth() {
        return this.health;
    }

    public void decreaseHealth(int factor) {
        this.health -= factor;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public String getName() {
        return this.name;
    }

    public int[] getPosition() {
        return new int[]{positionX, positionY};
    }

    public void decreaseMissileCount() {
        this.missileCount -= 1;
        if (this.missileCount < 0) {
            this.missileCount = 0;
        }
    }

    public int getMissileCount() {
        return this.missileCount;
    }

    public Missile getMissile() {
        try {
            return this.missiles.get(0);
        } catch (Exception exception) {
            return null;
        }
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

    public void addMissile(Missile missile) {
        this.missiles.add(0, missile);
    }

    public void popMissile() {
        try {
            this.missiles.remove(0);
        } catch (Exception ignored) {

        }
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

    public void increaseMissileCount(int factor) {
        this.missileCount += factor;
    }

    public boolean canShoot() {
        return this.missileCount > 0;
    }
}