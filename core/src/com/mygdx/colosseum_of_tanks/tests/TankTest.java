package com.mygdx.colosseum_of_tanks.tests;

import static org.junit.Assert.assertEquals;

import com.mygdx.colosseum_of_tanks.sprites.HeliosTank;
import com.mygdx.colosseum_of_tanks.sprites.Tank;

import org.junit.Test;

public class TankTest {
    @Test (expected = NullPointerException.class)
    public void run() {
        Tank tank = new HeliosTank(10, 10, true);
        assertEquals(6, tank.getMissileCount());
    }
}
