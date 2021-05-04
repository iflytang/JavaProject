package src.nowcoder.SwordOffer;

import com.sun.prism.j2d.J2DPipeline;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，
 * 计算该最长子字符串的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 * 提示：
 *
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */

public class Offer48 {

    public int lengthOfLongestSubstring(String s) { // 仅限于26字母
        int [] charIdx = new int[26];

        for (int i = 0; i < charIdx.length; i++) {
            charIdx[i] = -1;
        }

        int curLen = 0;
        int maxLen = 0;
        for (int j = 0; j < s.length(); j++) {  // s[i]=s[j]
            int charNum = s.charAt(j) - 'a';
            int i = charIdx[charNum];

            if (curLen < (j - i)) {  // f[j]=f[j-1]+1,f[j-1]<j-i
                curLen++;
            } else {  // f[j]=j-i,f[j-1]>=j-i
                curLen = j - i;
            }

            maxLen = Math.max(curLen, maxLen);

            charIdx[charNum] = j;
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) { // 不限字母，使用hashmap来记录index
        HashMap<Character, Integer> hashMap = new HashMap<>();

        int curLen = 0;
        int maxLen = 0;

        for (int j = 0; j < s.length(); j++) {
            int i = hashMap.getOrDefault(s.charAt(j), -1);
            int d = j - i;

            if (d <= curLen) {
                curLen = d;
            } else {
                curLen++;
            }

            maxLen = Math.max(maxLen, curLen);

            hashMap.put(s.charAt(j), j);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {  // 滑动窗口
        HashMap<Character, Integer> hashMap = new HashMap<>();

        int maxLen = 0;
        int i = -1;

        for (int j = 0; j < s.length(); j++) {
            if (hashMap.containsKey(s.charAt(j))) {
                i = Math.max(i, hashMap.get(s.charAt(j))); // 记录最左侧i
            }
            hashMap.put(s.charAt(j), j);

            maxLen = Math.max(maxLen, j - i);
        }

        return maxLen;
    }

    public static void main(String[] args) {

        Offer48 offer48 = new Offer48();

        String inputs = "abcabcbb";
        int ret = offer48.lengthOfLongestSubstring(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        inputs = " ";
        ret = offer48.lengthOfLongestSubstring2(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        inputs = "assume";
        ret = offer48.lengthOfLongestSubstring3(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        inputs = " ";
        ret = offer48.lengthOfLongestSubstring3(inputs);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





