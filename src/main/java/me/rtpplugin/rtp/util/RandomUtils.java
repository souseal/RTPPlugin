package me.rtpplugin.rtp.util;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int getRandomCoordinate(int radius) {
        return random.nextInt(radius * 2) - radius;
    }
}