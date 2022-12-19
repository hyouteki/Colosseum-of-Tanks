package com.mygdx.colosseum_of_tanks.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MusicHandler implements Serializable {
    public boolean status;
    public static final boolean DEFAULT_STATUS = false;
    public Music music;

    public MusicHandler(boolean status) {
        this.status = status;
        this.music = Gdx.audio.newMusic(Gdx.files.internal("music/rock_music.mp3"));
    }

    public void switchStatus() {
        this.status = !this.status;
    }

    public void playMusic() {
        this.music.play();
        this.music.setVolume(0.2f);
        this.music.setLooping(true);
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void handleMusic() {
        if (this.status) {
            playMusic();
        } else {
            stopMusic();
        }
    }

    public void serialize() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(String.valueOf(Gdx.files.internal("files/music.txt"))));
            objectOutputStream.writeObject(this);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static MusicHandler deserialize() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(String.valueOf(Gdx.files.internal("files/music.txt"))));
            return (MusicHandler) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        MusicHandler musicHandler = new MusicHandler(true);
//        musicHandler.serialize();
    }
}
