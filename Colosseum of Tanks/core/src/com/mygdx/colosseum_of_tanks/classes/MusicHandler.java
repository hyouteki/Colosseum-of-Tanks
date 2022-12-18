package com.mygdx.colosseum_of_tanks.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.Serializable;

public class MusicHandler implements Serializable {
    public static boolean status;
    public static final boolean DEFAULT_STATUS = false;
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("music/rock_music.mp3"));
    private static MusicHandler musicHandler = null;
    private MusicHandler(boolean status) {
        MusicHandler.status = status;
    }
    public MusicHandler getInstance() {
        if (musicHandler == null) {
            musicHandler = new MusicHandler(DEFAULT_STATUS);
        }
        return musicHandler;
    }
    public void switchStatus() {
        MusicHandler.status = !MusicHandler.status;
    }
    public void playMusic() {
        MusicHandler.music.play();
        MusicHandler.music.setVolume(0.2f);
        MusicHandler.music.setLooping(true);
    }
    public void stopMusic() {
        MusicHandler.music.stop();
    }
    public void handleMusic() {
        if (MusicHandler.status) {
            playMusic();
        } else {
            stopMusic();
        }
    }
}
