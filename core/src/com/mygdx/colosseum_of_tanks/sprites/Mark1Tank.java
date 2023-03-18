package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Mark1Tank extends Tank {
    public Mark1Tank(int position_x, int position_y, boolean flipped) {
        super(position_x, position_y, flipped);
        this.name = "Mark-1";
        this.tankImage = new Texture(Gdx.files.internal("images/tanks/mark_1.png"));
        this.tankImageFlipped = new Texture(Gdx.files.internal("images/tanks/mark_1-flipped.png"));
        this.tankCard = new Texture(Gdx.files.internal("images/tanks/mark_1-card.png"));
        this.tankCardFlipped = new Texture(Gdx.files.internal("images/tanks/mark_1-card-flipped.png"));
    }
}
