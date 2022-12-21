package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.colosseum_of_tanks.TheGame;

public class Drop extends Sprite {
    private final Missile missile;
    private final float positionX;
    private float positionY;
    private final int speed = 10;
    private static final float GROUND_LEVEL = 6 * 32 / TheGame.PPM;
    private static final float SKY_LEVEL = 14 * 32 / TheGame.PPM;
    private boolean landed;

    public Drop(float positionX) {
        this.positionX = positionX;
        this.positionY = SKY_LEVEL;
        this.landed = false;
        this.missile = new Explosive(0, 0, 0, false);
        setTexture(new Texture("images/drop/drop-box.png"));
        setRegion(new Texture("images/drop/drop-box.png"));
        setBounds(positionX, positionY, 48 / TheGame.PPM, 48 / TheGame.PPM);
    }

    public float getPositionX() {
        return this.positionX;
    }


    public void update(float delta) {
        if (!this.landed) {
            this.positionY -= 0.05;
        }
        if (this.positionY <= Drop.GROUND_LEVEL) {
            this.landed = true;
            this.positionY = Drop.GROUND_LEVEL;
        }
        setPosition(this.positionX, this.positionY);
    }

    public Missile releaseDrop() {
        return this.missile;
    }

}
