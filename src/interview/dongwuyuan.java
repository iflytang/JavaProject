package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class dongwuyuan {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {

            ArrayList<String> list = new ArrayList<>();
            list.add(str);

            while (str.contains("[")) {
                list.add(br.readLine());
            }

            int N = Integer.parseInt(br.readLine());
            int src = Integer.parseInt(br.readLine());

            Map<Integer, ArrayList<Integer>> dep_node = new LinkedHashMap<>();    // 节点依赖
            Map<Integer, ArrayList<Integer>> dep_dis = new LinkedHashMap<>();     // 依赖的距离

            Map<String, Integer> edge_dis = new LinkedHashMap<>();

            int [][] cost = new int[101][101];

            for (int i = 0; i < list.size(); i++) {
                String [] substr = list.get(i).split(",");
                int s = Integer.parseInt(substr[0].substring(1));
                int d = Integer.parseInt(substr[1]);
                int dis = Integer.parseInt(substr[2].substring(0, substr[2].length() - 1));
                cost[s][d] = dis;

                String key = s + String.valueOf(d);
                edge_dis.put(key, dis);

                if (dep_node.containsKey(s)) {  // 添加依赖
                    dep_node.get(s).add(d);
                    dep_dis.get(s).add(dis);
                } else {  // 初始化
                    ArrayList<Integer> nodes = new ArrayList<>();
                    nodes.add(d);
                    dep_node.put(s, nodes);

                    ArrayList<Integer> distances = new ArrayList<>();
                    distances.add(dis);
                    dep_dis.put(s, distances);
                }
            }

            Set<Integer> allNode = new HashSet<>();
            int maxLen = 0;
            int dep_nodes = dep_node.get(src).size();
            for (int i = 0; i < dep_nodes; i++) {
                int nextNode = dep_node.get(src).remove(i);

                int path_len = cost[src][nextNode];

            }
        }
    }
}

//public static int dfs(int [][] cost, int src, Map<Integer, ArrayList<Integer>> dep_node, Map<String, Integer> edges_dis) {
//    if (dep_node.get(src).size() == 0) {
//        return 0;
//    }
//
//    if (dep_node.get(src).size() == 1) {   // 只有一个节点时
//        int nextNode = dep_node.get(src).get(0);
//        return edges_dis.get(src + String.valueOf(nextNode));
//    }
//
//
//}


class node {
    List<node> next;
    int distance;

    public node (int distance) {
        this.distance = distance;
    }
}
