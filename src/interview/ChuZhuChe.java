package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 相邻站点时间固定为5，出租车可顺时或逆时，按最短路径，计算最多有多少辆出租车同时运营
 *
 * 第一行：N站点数，K乘客数，2<=N<=100,0<=K<=10000
 * 之后K行，使用车辆起始时间、上车ID、下车ID
 *
 * 输入：
 * 50 3
 * 0 0 15
 * 10 10 11
 * 15 20 40
 * 输出
 * 2
 *
 * 输入：
 * 10 3
 * 0 0 1
 * 5 0 9
 * 10 0 1
 * 输出
 * 1
 *
 * 输入：
 * 2 3
 * 0 0 1
 * 0 1 0
 * 输出：
 * 2
 */

public class ChuZhuChe {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] NK = str.split(" ");
            int n = 0;
            int k = 0;

            n = Integer.parseInt(NK[0]);
            k = Integer.parseInt(NK[1]);

            int [][] slots = new int[k][2];   // [起始时间，结束时间]
            for (int i = 0; i < k; i++) {
                String [] strs = br.readLine().split(" ");
                slots[i][0] = Integer.parseInt(strs[0]);  // 起始时间
                int dis = Math.abs(Integer.parseInt(strs[2]) - Integer.parseInt(strs[1]));
                slots[i][1] = slots[i][0] + Math.min(dis, n - dis) * 5;   // 结束时间
            }

            int maxNum = maxTaxi(slots);
            System.out.println(maxNum);
        }

    }

    public static int maxTaxi(int [][] slots) {
        Arrays.sort(slots, new Comparator<int[]>() {   // 按起始时间升序排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int []> list = new ArrayList<>();
        int res = 0;

        for (int i = 0; i < slots.length; i++) {
            Collections.sort(list, new Comparator<int[]>() {  // 按结束时间升序排序
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            // 如果乘客的上下车时间相同
            if ((list.size() > 0) && (list.get(0)[0] == slots[i][0]) && (list.get(0)[1] == slots[i][1])) {
                list.add(new int[] {slots[i][0], slots[i][1]});
                res = Math.max(res, list.size());
                continue;
            }

            // 乘客上车时，根据结束时间检查下车乘客（删掉下车乘客）
            while (list.size() > 0) {
                if (list.get(0)[1] <= slots[i][0]) {  // 新乘客上车（startTime），旧乘客已下车（endTime）
                    list.remove(0);
                } else {
                    break;
                }
            }

            // 添加当前乘客
            list.add(new int[] {slots[i][0], slots[i][1]});
            res = Math.max(res, list.size());

        }

        return  res;
    }

}
