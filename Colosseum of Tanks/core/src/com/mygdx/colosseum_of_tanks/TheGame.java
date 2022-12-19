package com.mygdx.colosseum_of_tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.colosseum_of_tanks.screens.StartScreen;

public class TheGame extends Game {
    public static final int SAVE_GAME = 3;
    public static final int NEW_GAME = 1;
    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 227;
    public static final float PPM = 100;
    public static final int HEALTH_BIT = 1;
    public static final int FUEL_BIT = 2;
    public static final int MISSILE_BIT = 3;
    public static final int FUEL_DECREASE_FACTOR = 2;
    public static final Color LIGHT_BLUE = new Color(0.64705884f, 0.64705884f, 1f, 1f);
    public SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        setScreen(new StartScreen(this, TheGame.NEW_GAME));
    }

    @Override
    public void render() {
        super.render();
    }
}
