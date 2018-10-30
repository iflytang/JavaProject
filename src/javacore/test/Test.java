package javacore.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by tsf on 17-11-22.
 */


public class Test {
    Map<String, String> macId = new HashMap<>();

    public byte getBit(byte base, int position) {
        return  (byte) (base & (0x01 << position));
//        return  (base & (0x01 << position)) != 0;
    }

    public Boolean getBoolBit(byte base, int position) {
        return (byte) (base & (0x01 << position)) != 0x00;
    }

    public int linearHashCode(List<Integer> paras) {
        final int prime = 31;
        int result = 1;

        for (Integer para : paras) {
            result = prime * result + para.hashCode();
        }

        System.out.printf("hashCode: " + result + "\n");

        return result;
    }

    public enum  INT_METADATA {
        DEVICE_ID,
        IN_PORT,
        OUT_PORT,
        IGRESS_TIME,
        EGRESS_TIME,
        BANDWIDTH,
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.macId.put("11:22:33:44:55:66", "10.0.1.1");
        String storedIp = test.macId.get("11:22:33:44:55:66");
        if(storedIp == null) {
            test.macId.put("11:22:33:44:55:66", "10.0.0.1");
            test.macId.put("01:02:03:04:05:06", "10.0.0.2");
        }

//        System.out.println("storedIP: " + storedIp);
//        System.out.println("macId: " + test.macId);
//        System.out.println(!"A".equals("b"));

        short ueId = 0xff;
        if (ueId != 0xff) {
//            System.out.println("ueId?: " + ueId);
        }
//        System.out.println("ueId: " + ueId);

        String a = " 123";
        if (a.charAt(0) == ' ') {
//            System.out.println("hello");
        }
        char result = a.charAt(1);
        int sum = result;
//        System.out.println(-result);
//        System.out.println((((long) Integer.MAX_VALUE + 1)));

        double big_num = 1000.555;
        BigDecimal decimal = new BigDecimal(big_num);
//        System.out.println("decimal: " + decimal.setScale(2, BigDecimal.ROUND_UP));

        byte byte_num = (byte) 0x1f;
        byte[] byte_arr = ByteBuffer.allocate(1).put(byte_num).array();
//        System.out.println(byte_num + " has " +  BitSet.valueOf(byte_arr).cardinality() + " bits of 1.");
//        System.out.println(byte_num + " has " + Integer.bitCount(byte_num) + " bits of 1.");
//        System.out.println(test.getBit(byte_num, 1) + " " + test.getBoolBit(byte_num, 4));

//        System.out.println(INT_METADATA.DEVICE_ID.ordinal());
//        System.out.println(INT_METADATA.IN_PORT.ordinal());

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        list2.add(11);
        list2.add(22);
        list2.add(33);
        list2.add((int) 44.81);
        test.linearHashCode(list);
        test.linearHashCode(list2);

        Map<String, String> map = new HashMap<>();
        map.put("sel", "1");
        map.put("sel", "2");
        System.out.println(map.get("sel") + " " + list.contains(51));

    }
}
