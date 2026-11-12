package com.alan.javatest.player;

import java.util.Random;

public class Dice {
    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int rol(){
        return new Random().nextInt(sides + 1);
    }
}
