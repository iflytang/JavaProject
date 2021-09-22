package src.interview;

import java.util.Random;

public class geneRadomNum {
    public static void generateRandom(int min, int max) {
        Random random = new Random();
        int rnd1 = random.nextInt(max) % (max - min + 1) + min;
        int rnd2 = random.nextInt(max - min) + min;
        int rnd3 = (int) (Math.random() * (max - min) + min);
        System.out.println(rnd1);
        System.out.println(rnd2);
        System.out.println(rnd3);
    }

    public static void main(String [] args) {
        generateRandom(0, 10);
    }
}
