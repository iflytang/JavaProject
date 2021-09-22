package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class modules {  // 能检测环

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        String str = br.readLine();
        int module = Integer.parseInt(str.substring(6));

        while ((str = br.readLine()) != null) {

            if (str.equals(""))
                break;

            String [] substr = str.split(",");
            int dep_module = Integer.parseInt(substr[0].substring(6));
            int time = Integer.parseInt(substr[1]);

            ArrayList<Integer> dep_info = new ArrayList<>();
            dep_info.add(time);

            for (int i = 2; i < substr.length; i++) {
                dep_info.add(Integer.parseInt(substr[i].substring(6)));
            }

            map.put(dep_module, dep_info);
        }

        if (ret >= 0)
            System.out.println(dfs(module, map));
        else
            System.out.println(ret);
    }

    static int ret = 0;
    static HashSet<Integer> visited = new HashSet<>();
    static int dfs(int module, HashMap<Integer, ArrayList<Integer>> dep_info) {

        if (dep_info.get(module) == null) {
//            System.out.println("here");
            ret = -1;
            return -1;
        }

        if (visited.contains(module)) {
            ret = -1;
            return -1;
        }

        if (dep_info.get(module).size() == 1) {
            return dep_info.get(module).get(0);  // 不依赖的时间
        }

        int res = dep_info.get(module).get(0);
        int dep = 0;
        visited.add(module);
        for (int i = 1; i < dep_info.get(module).size(); i++) {
            int dep_module = dep_info.get(module).get(i);
            dep = Math.max(dep, dfs(dep_module, dep_info));
        }

        return ret >= 0 ? (res + dep) : -1;
    }
}
