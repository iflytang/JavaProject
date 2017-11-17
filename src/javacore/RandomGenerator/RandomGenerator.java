package javacore.RandomGenerator;

import java.util.Random;

/**
 * Created by tsf on 17-11-17.
 */


public class RandomGenerator {

    public int randomGeneratorByRandom() {
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        System.out.println("random num by random: " + num);
        return num;
    }

    public int randomGeneratorByMath() {
        int num = (int) (Math.random() * 100) + 1;
        System.out.println("random num by math: " + num);
        return num;
    }

    public static void main(String[] args) {
        RandomGenerator test = new RandomGenerator();
        test.randomGeneratorByRandom();
        test.randomGeneratorByMath();
    }
}
