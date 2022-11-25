package com.mygdx.colosseum_of_tanks.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HeliosTank extends Tank {
    public HeliosTank() {
        super();
        setTexture(new Texture(Gdx.files.internal("images/tanks/helios.png")));
    }

    public HeliosTank(int position_x, int position_y) {
        super(position_x, position_y);
    }
}
