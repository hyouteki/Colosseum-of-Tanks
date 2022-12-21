package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.Texture;

public class Explosive extends Missile {
    public Explosive(float positionX, float positionY, int angle, boolean flipped) {
        super(positionX, positionY, angle, flipped);
        this.speed = 500;
        this.damage = 70;
        this.name = "Explosive";
        setTexture(new Texture("images/missiles/explosive.png"));
        setRegion(new Texture("images/missiles/explosive.png"));
    }
}
