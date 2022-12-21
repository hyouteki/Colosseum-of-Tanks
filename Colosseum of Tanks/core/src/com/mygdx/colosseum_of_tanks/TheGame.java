package com.mygdx.colosseum_of_tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.colosseum_of_tanks.screens.PlayScreen;
import com.mygdx.colosseum_of_tanks.screens.StartScreen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    public static PlayScreen slot1 = null;
    public static PlayScreen slot2 = null;
    public static PlayScreen slot3 = null;
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

    public void serialize(PlayScreen playScreen, int slot) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream("hello.txt"));
            objectOutputStream.writeObject(playScreen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
