package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ZhiShiTuPu {

    static List<String> res = new ArrayList<>();

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            String [][] substr = new String[n][3];
            for (int i = 0; i < n; i++) {
                substr[i] = br.readLine().split(" ");
            }

            String className = br.readLine();

            Map<String, List<String>> subclass = new HashMap<>();
            Map<String, List<String>> instance = new HashMap<>();

            for (int i = 0; i < n; i++) {

                if (substr[i][1].equals("instanceOf")) {

                    List<String> instanceInfo;
                    if (!instance.containsKey(substr[i][2])) {  // 如果是实例
                        instanceInfo = new ArrayList<>();
                    } else {
                        instanceInfo = instance.get(substr[i][2]);
                    }
                    instanceInfo.add(substr[i][0]);
                    instance.put(substr[i][2], instanceInfo);

                } else if (substr[i][1].equals("subClassOf")) {

                    List<String> classInfo;
                    if (!subclass.containsKey(substr[i][2])) {  // 如果是依赖
                        classInfo = new ArrayList<>();
                    } else {
                        classInfo = subclass.get(substr[i][2]);
                    }
                    classInfo.add(substr[i][0]);
                    subclass.put(substr[i][2], classInfo);

                }

            }

            res = new ArrayList<>();
            List<String> subClassSet = subclass.get(className);

            if (instance.containsKey(className)) {
                res.addAll(instance.get(className));
            }

            if (subClassSet != null && !subClassSet.isEmpty()) {
//                for (int i = 0; i < subClassSet.size(); i++) {
//                    res.addAll(instance.get(subClassSet.get(i)));
//                }
                getRes(subClassSet, subclass, instance);
            }
            Collections.sort(res);

            if (res.isEmpty()) {
                System.out.println("empty");
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.size(); i++) {
                sb.append(res.get(i)).append(" ");
            }
            System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
        }
    }

    // 回溯求依赖
    public static void getRes(List<String> subset, Map<String, List<String>> subclass,
                              Map<String, List<String>> instance) {
        if (subset != null && !subset.isEmpty()) {
            for (int i = 0; i < subset.size(); i++) {
                res.addAll(instance.get(subset.get(i)));
                getRes(subclass.get(subset.get(i)), subclass, instance);
            }
        }
    }

}

//4
//student subClassOf person
//Tom instanceOf student
//Marry instanceOf person
//Alice instanceOf person
//person
