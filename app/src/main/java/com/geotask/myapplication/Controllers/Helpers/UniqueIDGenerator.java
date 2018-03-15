package com.geotask.myapplication.Controllers.Helpers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public final class UniqueIDGenerator {
    private static final UniqueIDGenerator generator = new UniqueIDGenerator();
    private static String source = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static Random random = new Random();

    private UniqueIDGenerator() {
    }

    public static String generate() {
        String result = "";
        for(int i = 0; i < 16; i++) {
            result += source.charAt(random.nextInt(source.length()));
        }
        return result;
    }
}
