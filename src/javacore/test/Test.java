package javacore.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tsf on 17-11-22.
 */


public class Test {
    Map<String, String> macId = new HashMap<>();

    public static void main(String[] args) {
        Test test = new Test();
        test.macId.put("11:22:33:44:55:66", "10.0.1.1");
        String storedIp = test.macId.get("11:22:33:44:55:66");
        if(storedIp == null) {
            test.macId.put("11:22:33:44:55:66", "10.0.0.1");
            test.macId.put("01:02:03:04:05:06", "10.0.0.2");
        }

        System.out.println("storedIP: " + storedIp);
        System.out.println("macId: " + test.macId);
        System.out.println(!"A".equals("b"));
    }
}
