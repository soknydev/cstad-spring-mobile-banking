package co.istad.banking.init;

import java.util.Random;

public class RandomUuid {
    public static String generate9Digits() {
        Random random = new Random();
        return String.format("%09d", random.nextInt(1000000000));
    }

}
