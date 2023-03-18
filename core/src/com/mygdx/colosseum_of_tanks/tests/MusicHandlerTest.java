package com.mygdx.colosseum_of_tanks.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mygdx.colosseum_of_tanks.tools.MusicHandler;

import org.junit.Test;

public class MusicHandlerTest {
    @Test
    public void run() {
        MusicHandler musicHandler = MusicHandler.getInstance();
        assertFalse(musicHandler.getStatus());
        musicHandler.switchStatus();
        assertTrue(musicHandler.getStatus());
    }
}
