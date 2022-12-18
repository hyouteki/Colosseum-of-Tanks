package com.mygdx.colosseum_of_tanks;

import com.badlogic.gdx.Game;
import com.mygdx.colosseum_of_tanks.screens.StartScreen;

public class TheGame extends Game {
    public static final int SAVE_GAME = 3;
    public static final int NEW_GAME = 1;
    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 227;
    public static final float PPM = 100;

    @Override
    public void create() {
        setScreen(new StartScreen(this, TheGame.NEW_GAME));
    }

    @Override
    public void render() {
        super.render();
    }
}
