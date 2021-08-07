package book.q16;

import java.util.Arrays;

/**
 * 字符串的交错组成
 *
 * @author iterators
 * @since 2021/08/01
 */
public class Solution {

    public static void main(String[] args) {
        String str1 = "AB";
        String str2 = "12";
        String aim = "AB12";
        boolean result = isStaggeredComposition(str1.toCharArray(), str2.toCharArray(), aim.toCharArray());
        System.out.println("result = " + result);
    }

    /**
     * 判断aim是否是str1和str2的交错组成
     * @param str1
     * @param str2
     * @param aim
     */
    private static boolean isStaggeredComposition(char[] str1, char[] str2, char[] aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }

        // 字符串aim的长度不等于字符串str1与字符串str2的长度之和，那么aim就不可能包含且仅包含来自str1和str2的所有字符
        if (aim.length != str1.length + str2.length) {
            return false;
        }
        // dp[i][j]表示字符串str1[0...i]与字符串str2[0...j]的交错组成
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];

        // base case
        // 当字符串是str1为空和str2为空时，空字符串时两者的交错组成
        dp[0][0] = true;

        // dp[0][j]：str1为空，str2不为空，此时str2[0...j] == aim[0...j]时，aim是二者的交错组成
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] && str2[i - 1] == aim[i-1];
        }

        // dp[i][0]
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] && str1[i - 1] == aim[i - 1];
        }

        // dp[i][j]满足
        // 1: dp[i-1][j]表示str1[0...i-2]和str2[0...j-1]的交错组成结果，如果此时满足str1[i-1]==aim[i+j-1], 那么str1[i-1]可以作为上一个的最有一个交错组成部分，dp[i][j] == true
        // 2: dp[i][j-1]同理
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = (dp[i - 1][j] && str1[i - 1] == aim[i + j - 1]) || (dp[i][j - 1] && str2[j - 1] == aim[i + j - 1]);
            }
        }

        printDp(dp);
        return dp[str1.length][str2.length];
    }

    private static void printDp(boolean[][] dp) {
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }
}
