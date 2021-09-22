package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 无环有向图的最大路径。
 *
 * 输入描述：
 * 链路集合，每条链路由源节点，目的节点和边的权重表示，链路存在方向，链路条数<=200；边的权重<=200
 *
 * 如：
 * [[1,2,5],[1,3,5],[4,2,10],[2,5,5],[3,4,10],3,7,10],[4,7,5],[5,6,5],[6,7,5]]
 *
 * 输出描述：
 * 最长路径的开销值
 * 如：
 * 40
 *
 */

public class LongestPath {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        // [[1,2,5],[1,3,5],[4,2,10],[2,5,5],[3,4,10],[3,7,10],[4,7,5],[5,6,5],[6,7,5]]
        while ((str = br.readLine()) != null) {
            str = str.substring(2, str.length() - 2);
            String [] strs = str.split("],\\[");

            int [][] link = new int[strs.length][3];
            for (int i = 0; i < link.length; i++) {
                String [] linkStr = strs[i].split(",");
                link[i][0] = Integer.parseInt(linkStr[0]);
                link[i][1] = Integer.parseInt(linkStr[1]);
                link[i][2] = Integer.parseInt(linkStr[2]);
            }

            int maxPathLen = maxLen(link);

            System.out.println(maxPathLen);
        }

    }

    public static int maxLen(int [][] nums) {
        Set<Integer> dicSrc = new HashSet<>();
        Set<Integer> dicDst = new HashSet<>();

        int maxNode = 0;  // 最大节点ID
        for (int i = 0; i < nums.length; i++) {
            dicSrc.add(nums[i][0]);
            dicDst.add(nums[i][1]);
            maxNode = Math.max(maxNode, Math.max(nums[i][0], nums[i][1]));
        }

        ArrayList<Integer> endList = new ArrayList<>();
        for (int node = 1; node <= maxNode; node++) {
            // 如果源节点集合中没有入度节点，那么这个节点就是最后一个端节点
            if (!dicSrc.contains(node)) {
                endList.add(node);  // 可能包含多个端节点
            }
        }

        int maxPathLen = 0;
        for (int i = 0; i < endList.size(); i++) {
            maxPathLen = Math.max(maxPathLen, dfs(nums, endList.get(i)));  // 从目的端节点开始遍历
        }

        return maxPathLen;
    }

    public static int dfs(int [][] nums, int end) {
        ArrayList<int[]> preNodeList = new ArrayList<>();

        for (int j = 0; j < nums.length; j++) {
            if (nums[j][1] == end) {
                preNodeList.add(new int[]{nums[j][0], nums[j][2]});
            }
        }

        if (preNodeList.size() == 0) {
            return 0;
        }

//        if (preNodeList.size() == 1) {
//            return dfs(nums, preNodeList.get(0)[0]) + preNodeList.get(0)[1];
//        }

        int maxPathLen = 0;
        for (int j = 0; j < preNodeList.size(); j++) {
            maxPathLen = Math.max(maxPathLen, dfs(nums, preNodeList.get(j)[0])+ preNodeList.get(j)[1]);
        }

        return maxPathLen;
    }


}
