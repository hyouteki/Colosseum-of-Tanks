package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.colosseum_of_tanks.TheGame;

public class Missile extends Sprite {
    protected int star;
    protected int damage;
    protected float positionX;
    protected float positionY;
    protected int speed;
    protected String name;
    protected boolean remove;
    protected int angle;
    protected boolean flipped;
    protected static final float RADIAN_FACTOR = 0.0174533f;
    protected static final float GRAVITY = 9.8f;

    public Missile(float positionX, float positionY, int angle, boolean flipped) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.angle = angle;
        this.flipped = flipped;
        this.remove = false;
        setPosition(this.positionX, this.positionY);
        setBounds(this.positionX, this.positionY, 16 / TheGame.PPM, 16 / TheGame.PPM);
    }

    public float getPositionX() {
        return this.positionX;
    }

    public void setMotionPosition(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void update(float delta) {
        // x = v.cosQ.t
        // y = v@.sin@Q/2g
//        this.positionX = (float) ((this.speed) * (Math.cos(Missile.RADIAN_FACTOR * (this.angle))) * delta);
//        this.positionY = (float) ((this.speed) * (this.speed) * (Math.sin(Missile.RADIAN_FACTOR * (this.angle))) * (Math.sin(Missile.RADIAN_FACTOR * (this.angle))) / 2 / Missile.GRAVITY);
        if (this.flipped) {
            this.positionX -= this.speed / TheGame.PPM * delta;
        } else {
            this.positionX += this.speed / TheGame.PPM * delta;
        }
        setPosition(this.positionX, this.positionY);
    }

    public void render(SpriteBatch batch) {
        if (!remove) {
            this.draw(batch);
        }
    }

    public void remove() {
        this.remove = true;
    }

    public String getName() {
        return this.name;
    }

    public int get_star() {
        return star;
    }

    public void set_star(int star) {
        this.star = star;
    }

    public int getDamage() {
        return damage;
    }

    public void set_max_damage(int max_damage) {
        this.damage = max_damage;
    }
}
