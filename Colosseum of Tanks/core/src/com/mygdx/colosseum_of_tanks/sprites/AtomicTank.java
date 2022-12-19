package com.mygdx.colosseum_of_tanks.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AtomicTank extends Tank {
    public AtomicTank(int position_x, int position_y, boolean flipped) {
        super(position_x, position_y, flipped);
        this.tankImage = new Texture(Gdx.files.internal("images/tanks/atomic.png"));
        this.tankImageFlipped = new Texture(Gdx.files.internal("images/tanks/atomic-flipped.png"));
        this.tankCard = new Texture(Gdx.files.internal("images/tanks/atomic-card.png"));
        this.tankCardFlipped = new Texture(Gdx.files.internal("images/tanks/atomic-card-flipped.png"));
    }
}
