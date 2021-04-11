package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
 * （也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */


public class LCS {

    /* 递归的方法：自顶向下 */
    public int longestCommonSubsequenceI(String text1, String text2) {
        // 递归基
        if ((text1.length() <= 0) || (text2.length() <= 0)) {
            return 0;
        }

        int case1 = 0, case2 = 0, case3 = 0;
        if (text1.charAt(text1.length() - 1) == (text2.charAt(text2.length() - 1))) {
            // 如果后缀相同
            return longestCommonSubsequenceI(text1.substring(0, text1.length() - 1),
                    text2.substring(0, text2.length() - 1)) + 1;  // 最后一位相同，加1
        } else {

            // 如果后缀不同，两种情况
            case2 = longestCommonSubsequenceI(text1.substring(0, text1.length() - 1),
                    text2.substring(0, text2.length()));  // 最后一位不同，不加1

            case3 = longestCommonSubsequenceI(text1.substring(0, text1.length()),
                    text2.substring(0, text2.length() - 1));  // 最后一位不同，不加1
            
            return Math.max(case2, case3);
        }

    }

     /* 动态规划：自底向上 */
    public int longestCommonSubsequenceII(String text1, String text2) {
        int mLen = text1.length();
        int nLen = text2.length();

        int[][] LCS = new int[mLen + 1][nLen + 1];  // 表示最长前缀长度

        // 边界情况：text1空
        for (int i = 0; i <= mLen; i++) {
            LCS[i][0] = 0;
        }

        // 边界情况2：text2空
        for (int j = 0; j <= nLen; j++) {
            LCS[0][j] = 0;
        }

        // 动态归化
        for (int i = 1; i <= mLen; i++) {
            for (int j = 1; j <= nLen; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        return LCS[mLen][nLen];
        
    }

    public static void main(String [] args) {
        LCS lcs = new LCS();

        String text1 = "abcde";
        String text2 = "acde";
        int ret = lcs.longestCommonSubsequenceI(text1, text2);
        int ret2 = lcs.longestCommonSubsequenceII(text1, text2);
        System.out.println(ret + ", " + ret2);
        System.out.println("===============");

        text1 = "abc";
        text2 = "abc";
        ret = lcs.longestCommonSubsequenceI(text1, text2);
        ret2 = lcs.longestCommonSubsequenceII(text1, text2);
        System.out.println(ret + ", " + ret2);
        System.out.println("===============");

        text1 = "abc";
        text2 = "def";
        ret = lcs.longestCommonSubsequenceI(text1, text2);
        ret2 = lcs.longestCommonSubsequenceII(text1, text2);
        System.out.println(ret + ", " + ret2);
        System.out.println("===============");
    }
}
