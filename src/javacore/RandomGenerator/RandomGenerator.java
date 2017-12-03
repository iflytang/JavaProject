package javacore.RandomGenerator;

import java.util.*;

/**
 * Created by tsf on 17-11-17.
 */


public class RandomGenerator {
    protected Set<Integer> ueIdSet = new HashSet<>();
    protected List<Integer> ueIdList = new ArrayList<>();
    protected Map<String, Integer> Mac_UeId = new HashMap<>(); // store mac_ueId in Map

    public int ueIdGenerator(String mac) {
        // assign UeId
        Random random = new Random();
        int ueId = random.nextInt(10) + 1;  // ueId from 1 to 128
        int randRange = 10;   // at most 128 ueId
        int i;   // index of for loop

        // if ueId have assigned, forbid to reassign
        if(Mac_UeId.get(mac) != null) {
            System.out.println("Ue [" + mac + "] have assigned UeId [" + Mac_UeId.get(mac) + "], fail to reassign!");
            return Mac_UeId.get(mac);
        }

        // if ueIdList full, fail to assign ueId
        if(ueIdList.size() == randRange) {
            System.out.println("no more ueId! assign ueId fails, return with ueId<255>.");
            return 255;
        }

        // check in ueIdList in a traversal way
        for(i = 0; i < ueIdList.size(); i++) {
            // if assigned, reassign ueId and recheck
            Integer assignedId = ueIdList.get(i);
            if(ueId == assignedId) {
                int tempUeId = random.nextInt(10) + 1;
                System.out.println("Warning! ueId conflicts! There have been ueId ==> " + ueId + ", reassign ueId ==> " + tempUeId);
                ueId = tempUeId;
                i = 0;
                i--;     // i-- then i++, finally i = 0
            }
        }

        // no conflicts for ueId, then store in ueIdSet
        ueIdList.add(ueId);
        ueIdSet.add(ueId);
        Mac_UeId.put(mac, ueId);
        Collections.sort(ueIdList);     // sort ueId in ascending order
        System.out.println("==> ueId " + ueId + " store in ueIdSet " + ueIdSet + ", in ueIdList " + ueIdList);
        System.out.println("Mac_UeId " + Mac_UeId);
        return ueId;
    }

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
        test.ueIdGenerator("01:02:03:04:05:06");
        ueId = test.Mac_UeId.remove("01:02:03:04:05:06");
        System.out.println("ueId: " + ueId + ", result: " + test.Mac_UeId.get("01:02:03:04:05:06"));
        test.ueIdGenerator("11:22:33:44:55:66");
        test.ueIdGenerator("01:02:03:04:05:06");
//        for (int i = 0; i < 11; i++) {
//            ueId = test.ueIdGenerator(Integer.toString(i));
//            System.out.println("assign [" + i + "] random ueId by random: " + ueId + "\n");
//            if (i == 9) {
//                test.ueIdList.remove((Integer) 10);
//                System.out.println("remove id 10, now ueIdList ==> " + test.ueIdList);
//            }
//            test.randomGeneratorByMath();
//        }
//
//        test.ueIdGenerator(Integer.toString(0));

        // test remove according to index or object
 /*       List<Integer> list1 = new ArrayList<>();
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
    */
    }
}
