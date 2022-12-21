package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.graphics.Texture;

public class Grenade extends Missile {
    public Grenade(float positionX, float positionY, int angle, boolean flipped) {
        super(positionX, positionY, angle, flipped);
        this.speed = 500;
        this.damage = 35;
        this.name = "Grenade";
        setTexture(new Texture("images/missiles/grenade.png"));
        setRegion(new Texture("images/missiles/grenade.png"));
    }

    public Grenade(float positionX, float positionY, int angle, boolean flipped, int damage) {
        super(positionX, positionY, angle, flipped);
        this.damage = damage;
    }
}
