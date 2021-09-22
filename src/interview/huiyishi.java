package src.interview;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 力扣 252. 会议室
 * 难度：Easy
 *
 * 给定一个会议时间安排的数组 intervals ，
 * 每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1:：
 * 输入: intervals = [[0,30],[5,10],[15,20]]
 * 输出: false
 * 解释: 存在重叠区间，一个人在同一时刻只能参加一个会议。
 *
 * 示例 2:：
 * 输入: intervals = [[7,10],[2,4]]
 * 输出: true
 * 解释: 不存在重叠区间
 *
 * https://mp.weixin.qq.com/s/ioUlNa4ZToCrun3qb4y4Ow
 */

public class huiyishi {
    public static boolean solution (int [][] arr) {
        Arrays.sort(arr, new Comparator<int []>() {
            @Override
            public int compare(int [] o1, int [] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i][1] > arr[i + 1][0]) {  // 结束时间大于开始时间
                return false;
            }
        }

        return true;
    }

    public static void main (String [] args) {
        int [][] time1 = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println(solution(time1));

        int [][] time2 = {
                {7, 10},
                {2, 4}
        };

        System.out.println(solution(time2));
    }

}
