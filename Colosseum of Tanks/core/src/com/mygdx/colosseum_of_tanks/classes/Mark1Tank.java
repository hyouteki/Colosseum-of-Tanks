package com.mygdx.colosseum_of_tanks.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Mark1Tank extends Tank {
    public Mark1Tank() {
        super();
        setTexture(new Texture(Gdx.files.internal("images/tanks/mark_1.png")));
    }

    public Mark1Tank(int position_x, int position_y) {
        super(position_x, position_y);
    }
}
