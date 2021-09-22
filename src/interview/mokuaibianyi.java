package src.interview;

import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

//Author:pjq

public class mokuaibianyi {

    static HashMap<String, Integer> visited = new HashMap<>();
    static HashMap<String, List> relys = new HashMap<String, List>();
    static HashMap<String, Integer> times = new HashMap<>();

    private static boolean valid = true;
    static int ans = 0;
    static int cur_ans = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String start = in.nextLine();

        while(true) {
            String cur = in.nextLine();
            if(cur.equals("")) {
//                System.out.println("jieshu");
                break;
            }

            String[] curs = cur.split(",");
            int n = curs.length;

            times.put(curs[0], Integer.valueOf(curs[1]));
//            System.out.println(" " + curs[0] + " " + curs[1]);
            List<String> tmp = new ArrayList<>();

            for(int i = 2; i < n; i++) {
                tmp.add(curs[i]);
            }

            relys.put(curs[0], tmp);
        }

        if(!times.containsKey(start)) {
            valid = false;
            System.out.println(-1);
            return;
        }

        cur_ans += times.get(start);
        dfs(start);

//        System.out.println(valid);
        if(valid == false) System.out.println(-1);
        if(valid) System.out.println(ans);
    }

    public static void dfs(String a) {
        visited.put(a, 1);

        List<String> edges = relys.get(a);

        if(edges.size() == 0) {
//            System.out.println("here");
            ans = Math.max(ans, cur_ans);
        }

        for(String b : edges){
//            System.out.println("edges: " + b);
            if(!visited.containsKey(b)) {
                if(!times.containsKey(b)) {
                    valid = false;
                    return;
                }
                cur_ans += times.get(b);
                dfs(b);
                cur_ans -= times.get(b);
                if(!valid) return;
            } else if(visited.get(b) == 1) {
                valid = false;
                return;
            }
        }

        visited.put(a, 2);
        return;
    }
}