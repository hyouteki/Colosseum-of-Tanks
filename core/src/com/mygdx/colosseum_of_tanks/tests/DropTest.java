package com.mygdx.colosseum_of_tanks.tests;

import static org.junit.Assert.assertEquals;

import com.mygdx.colosseum_of_tanks.sprites.Drop;

import org.junit.Test;

public class DropTest {
    @Test(expected = NullPointerException.class)
    public void run() {
        Drop drop = new Drop(10);
        assertEquals(drop.getPositionX(), 10f);
    }
}
