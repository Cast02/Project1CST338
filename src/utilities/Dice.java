package utilities;

import java.util.Random;

public class Dice {

    public static int roll(int min, int max) {
        Random random = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        return (random.nextInt(max - Math.abs(min)) + 1) + min;
    }

    public static int roll(int max) {
        return Dice.roll(max, 1);
    }

}
