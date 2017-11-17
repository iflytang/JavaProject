package javacore.RandomGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by tsf on 17-11-17.
 */


public class RandomGenerator {
    protected Set<Integer> ueIdSet = new HashSet<>();

    public int randomGeneratorByRandom() {
        Random random = new Random();
        int ueId = random.nextInt(10) + 1;
//        System.out.println("random ueId by random: " + ueId);
        forloop:
        for(Integer assignedId : ueIdSet) {
            // if assigned, reassign ueId and recheck
            if(ueId == assignedId) {
                int tempUeId = random.nextInt(10) + 1;
                System.out.println("Warning! ueId conflicts! There have been ueId ==> " + ueId + ", reassign ueId ==> " + tempUeId);
                ueId = tempUeId;
                break forloop;
            }
        }
        ueIdSet.add(ueId);
        return ueId;
    }

    public int randomGeneratorByMath() {
        int num = (int) (Math.random() * 100) + 1;
        System.out.println("random num by math: " + num);
        return num;
    }

    public static void main(String[] args) {
        RandomGenerator test = new RandomGenerator();
        int ueId;
        for(int i = 0; i < 10; i++) {
            ueId = test.randomGeneratorByRandom();
            System.out.println("assign [" + i + "] random ueId by random: " + ueId);
//            test.randomGeneratorByMath();
        }
    }
}
