package javacore.RandomGenerator;

import java.util.*;

/**
 * Created by tsf on 17-11-17.
 */


public class RandomGenerator {
    protected Set<Integer> ueIdSet = new HashSet<>();
    protected List<Integer> ueIdList = new ArrayList<>();


    public int randomGeneratorByRandom() {
        Random random = new Random();
        int ueId = random.nextInt(10) + 1;
        int randRange = 10;
        int i;
        System.out.println("assign ueId in random: " + ueId);

        if(ueIdList.size() == randRange) {
            System.out.println("no more ueId! assign fails!");
            return 255;
        }

        //forloop:
        for(i = 0; i < ueIdList.size(); i ++) {
            // if assigned, reassign ueId and recheck
            Integer assignedId = ueIdList.get(i);
            System.out.println("assigned ueId in ueIdList ==> " + assignedId + ", i == > " + i);
            if(ueId == assignedId) {
                int tempUeId = random.nextInt(10) + 1;
                System.out.println("Warning! ueId conflicts! There have been ueId ==> " + ueId + ", reassign ueId ==> " + tempUeId);
                ueId = tempUeId;
                i = 0;
                i--;
               // break forloop;
            }
        }
        ueIdSet.add(ueId);
        ueIdList.add(ueId);
        Collections.sort(ueIdList);
        System.out.println("ueId " + ueId + " store in ueIdSet " + ueIdSet + ", in ueIdList " + ueIdList);
        return ueId;
    }

    public int randomGeneratorByMath() {
        int num = (int) (Math.random() * 100) + 1;
        System.out.println("random num by math: " + num);
        return num;
    }

    public List<Integer> removeUeId(Integer ueId) {
        ueIdList.remove(ueId);
        return ueIdList;
    }

    public static void main(String[] args) {
        RandomGenerator test = new RandomGenerator();
        int ueId;
        for(int i = 0; i < 11; i++) {
            ueId = test.randomGeneratorByRandom();
            System.out.println("assign [" + i + "] random ueId by random: " + ueId + "\n");
            if ( i == 9) {
                test.ueIdList.remove((Integer) 10);
                System.out.println("remove id 10, now ueIdList ==> " + test.ueIdList);
            }
//            test.randomGeneratorByMath();
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 10; i > 0; i --) {
            list1.add(i);
            list2.add(i);
        }
        System.out.println("remove id by Object: " + list1 + "; remove id by index: " + list2 );
        list1.remove((Integer) 9);
        list2.remove(9);
        test.ueIdList.remove(9);
        test.removeUeId(9);
        System.out.println("remove id by Object: " + list1 + "; remove id by index: " + list2 + ";\n remove id in ueIdList: " + test.ueIdList);
    }
}
