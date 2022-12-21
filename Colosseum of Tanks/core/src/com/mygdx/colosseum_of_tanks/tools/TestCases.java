package com.mygdx.colosseum_of_tanks.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mygdx.colosseum_of_tanks.sprites.Missile;

import org.junit.Test;

public class TestCases {
    @Test
    public void run() {
        Missile missile = new Missile(0, 0, 30, false);
        assertTrue(missile.getCheck());
        missile.update(1f);
        double xVelocity = missile.getXVelocity();
        double yVelocity = missile.getYVelocity();
        double totalTime = missile.totalTime();
        double timeIncrement = missile.getTimeIncrement();
        double xIncrement = missile.getXIncrement();
        assertFalse(missile.getCheck());
        missile.update(2f);
        assertEquals(0,xVelocity, 0);
        assertEquals(0,totalTime, 0);
    }
}
