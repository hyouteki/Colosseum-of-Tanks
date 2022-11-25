package com.mygdx.colosseum_of_tanks.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AtomicTank extends Tank {
    public AtomicTank() {
        super();
        setTexture(new Texture(Gdx.files.internal("images/tanks/atomic.png")));
    }

    public AtomicTank(int position_x, int position_y) {
        super(position_x, position_y);
    }
}
