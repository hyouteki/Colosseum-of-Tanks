package com.mygdx.colosseum_of_tanks.tools;

import com.mygdx.colosseum_of_tanks.sprites.AtomicTank;
import com.mygdx.colosseum_of_tanks.sprites.HeliosTank;
import com.mygdx.colosseum_of_tanks.sprites.Mark1Tank;
import com.mygdx.colosseum_of_tanks.sprites.Tank;

public class TankFactory {
    public static final int ATOMIC_TANK = 1;
    public static final int HELIOS_TANK = 2;
    public static final int MARK_1_TANK = 3;
    public static final int ATOMIC_TANK_FLIPPED = 4;
    public static final int HELIOS_TANK_FLIPPED = 5;
    public static final int MARK_1_TANK_FLIPPED = 6;

    public Tank getTankInstance(int option) {
        if (option == TankFactory.ATOMIC_TANK) {
            return new AtomicTank(Tank.DEFAULT_TANK1_START_POSITION_X,
                    Tank.DEFAULT_TANK1_START_POSITION_Y, true);
        } else if (option == TankFactory.HELIOS_TANK) {
            return new HeliosTank(Tank.DEFAULT_TANK1_START_POSITION_X,
                    Tank.DEFAULT_TANK1_START_POSITION_Y, true);
        } else if (option == TankFactory.MARK_1_TANK) {
            return new Mark1Tank(Tank.DEFAULT_TANK1_START_POSITION_X,
                    Tank.DEFAULT_TANK1_START_POSITION_Y, true);
        } else if (option == TankFactory.ATOMIC_TANK_FLIPPED) {
            return new AtomicTank(Tank.DEFAULT_TANK2_START_POSITION_X,
                    Tank.DEFAULT_TANK2_START_POSITION_Y, false);
        } else if (option == TankFactory.HELIOS_TANK_FLIPPED) {
            return new HeliosTank(Tank.DEFAULT_TANK2_START_POSITION_X,
                    Tank.DEFAULT_TANK2_START_POSITION_Y, false);
        } else if (option == TankFactory.MARK_1_TANK_FLIPPED) {
            return new Mark1Tank(Tank.DEFAULT_TANK2_START_POSITION_X,
                    Tank.DEFAULT_TANK2_START_POSITION_Y, false);
        } else {
            return null;
        }
    }
}
