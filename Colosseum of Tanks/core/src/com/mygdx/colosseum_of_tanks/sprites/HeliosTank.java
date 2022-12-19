package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HeliosTank extends Tank {
    public HeliosTank(int position_x, int position_y, boolean flipped) {
        super(position_x, position_y, flipped);
        this.tankImage = new Texture(Gdx.files.internal("images/tanks/helios.png"));
        this.tankImageFlipped = new Texture(Gdx.files.internal("images/tanks/helios-flipped.png"));
        this.tankCard = new Texture(Gdx.files.internal("images/tanks/helios-card.png"));
        this.tankCardFlipped = new Texture(Gdx.files.internal("images/tanks/helios-card-flipped.png"));
    }
}
