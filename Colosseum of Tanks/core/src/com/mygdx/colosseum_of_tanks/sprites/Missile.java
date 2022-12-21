package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.colosseum_of_tanks.TheGame;

public class Missile extends Sprite {
    protected int damage;
    protected float positionX;
    protected float positionY;
    protected int speed;
    protected String name;
    protected boolean remove;
    protected double angle;
    protected boolean flipped;
    protected static final float ACCELERATION = -9.81f;
    private double time = 0.0;
    private static final int STEPS = 120;
    public static final float GROUND_LEVEL = 32 * 6 / TheGame.PPM;

    private float velocity = 0;
    private boolean check = true;
    private double xVelocity;
    private double yVelocity;
    private double totalTime;
    private double timeIncrement;
    private double xIncrement;

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
        this.angle = Math.toRadians(angle);
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void update(float delta) {
        if (check) {
            check = false;
            this.velocity = this.speed * 10 / TheGame.PPM * delta;
            this.time += timeIncrement;
            this.xVelocity = velocity * Math.cos(angle);
            this.yVelocity = velocity * Math.sin(angle);
            this.totalTime = -2.0 * yVelocity / ACCELERATION;
            this.timeIncrement = totalTime / STEPS;
            this.xIncrement = xVelocity * timeIncrement;
        }
        if (angle == 0) {
            linearMotion(delta);
        } else {
            projectileMotion();
        }
    }

    public boolean getCheck() {
        return this.check;
    }

    public void projectileMotion() {
        if (flipped) {
            this.positionX -= xIncrement * TheGame.PPM;
            this.positionY = GROUND_LEVEL + (float) (yVelocity * time + 0.5 * ACCELERATION * time * time) * TheGame.PPM;
        } else {
            this.positionX += xIncrement * TheGame.PPM;
            this.positionY = GROUND_LEVEL + (float) (yVelocity * time + 0.5 * ACCELERATION * time * time) * TheGame.PPM;
        }
        this.time += timeIncrement;
        setPosition(positionX, positionY);
    }

    public void linearMotion(float delta) {
        if (this.flipped) {
            this.positionX -= this.speed / TheGame.PPM * delta;
        } else {
            this.positionX += this.speed / TheGame.PPM * delta;
        }
        setPosition(this.positionX, this.positionY);
    }

    public void remove() {
        this.remove = true;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return damage;
    }

    public float getPositionY() {
        return this.positionY;
    }

    public double getXVelocity() {
        return this.xVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public double totalTime() {
        return this.totalTime;
    }

    public double getTimeIncrement() {
        return this.timeIncrement;
    }

    public double getXIncrement() {
        return this.xIncrement;
    }
}
