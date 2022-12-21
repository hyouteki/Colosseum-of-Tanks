package com.mygdx.colosseum_of_tanks.tools;

import com.badlogic.gdx.audio.Music;

public class MusicHandler {
    private static boolean status;
    private static final boolean DEFAULT_STATUS = false;
    private static MusicHandler musicHandler = null;

    private MusicHandler() {
        MusicHandler.status = DEFAULT_STATUS;
    }

    public static MusicHandler getInstance() {
        if (MusicHandler.musicHandler == null) {
            MusicHandler.musicHandler = new MusicHandler();
        }
        return MusicHandler.musicHandler;
    }

    public void switchStatus() {
        MusicHandler.status = !MusicHandler.status;
    }

    public boolean getStatus() {
        return MusicHandler.status;
    }

    public void playMusic(Music music) {
        music.play();
        music.setVolume(0.2f);
        music.setLooping(true);
    }

    public void stopMusic(Music music) {
        music.stop();
    }

    public void handleMusic(Music music) {
        if (MusicHandler.status) {
            playMusic(music);
        } else {
            stopMusic(music);
        }
    }
}
