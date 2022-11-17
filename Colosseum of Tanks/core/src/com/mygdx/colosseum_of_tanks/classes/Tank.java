package com.mygdx.colosseum_of_tanks.classes;

import java.util.ArrayList;

public class Tank {
    protected int health;
    protected int position_x;
    protected int position_y;
    protected ArrayList<Missile> missiles;
    protected ArrayList<Missile> available_missiles;
    protected int missile_count;
    protected int fuel;

    public Tank(int position_x, int position_y) {
        this.health = 100;
        this.position_x = position_x;
        this.position_y = position_y;
        this.missiles = new ArrayList<>();
        this.available_missiles = new ArrayList<>();
        this.missile_count = 6;
        this.fuel = 100;
    }

    public void set_available_missiles(ArrayList<Missile> missiles) {
        this.available_missiles.addAll(missiles);
    }

    public void move_forward() {
        // TO-DO
    }

    public void move_backward() {
        // TO-DO
    }

    public void aim_missile(int index) {
        // Missile missile = this.missiles.get(index);
        // TO-DO
    }

    public void collect_drop(Drop drop) {
        this.missiles.addAll(drop.get_missiles());
    }

    public int get_health() {
        return this.health;
    }

    public void decrease_health(int factor) {
        this.health -= factor;
    }

    public void increase_health(int factor) {
        this.health += factor;
    }

    public int[] get_position() {
        return new int[]{position_x, position_y};
    }

    public void decrease_missile_count() {
        this.missile_count -= 1;
    }

    public int get_missile_count() {
        return this.missile_count;
    }

    public ArrayList<Missile> get_missiles() {
        return this.missiles;
    }

    public void decrease_fuel(int factor) {
        this.fuel -= factor;
    }

    public int get_fuel() {
        return this.fuel;
    }

    public void reset_fuel() {
        this.fuel = 100;
    }

    public void knock_back() {
        // TO-DO
    }

}