package src.interview;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public static final int SEG_CNT = 4;
    int [] segments = new int [SEG_CNT];
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        segments = new int [SEG_CNT];
        dfs (s, 0, 0);

        return res;
    }

    public void dfs (String s, int segmentId, int sgementStart) {
        // 递归出口：如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segmentId == SEG_CNT) {
            if (sgementStart == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < SEG_CNT; i++) {
                    sb.append(segments[i]).append(".");
                }

                res.add(sb.deleteCharAt(sb.length() - 1).toString());
            }

            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (sgementStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(sgementStart) == '0') {
            segments[segmentId] = 0;
            dfs (s, segmentId + 1, sgementStart + 1);
            return;
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segmentEnd = sgementStart; segmentEnd < s.length(); segmentEnd++) {
            addr = addr * 10 + (s.charAt(segmentEnd) - '0');

            if (addr > 0 && addr <= 0xff) {
                segments[segmentId] = addr;
                dfs (s, segmentId + 1, segmentEnd + 1);
            } else {
                break;
            }

        }

    }
}
