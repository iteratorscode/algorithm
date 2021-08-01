package book.q12;

import java.util.Arrays;

/**
 * 两个数组的最长公共子序列
 *
 * @author iterators
 * @since 2021/07/31
 */
public class Solution {

    public static void main(String[] args) {
        String str1 = "1A2C3D4B56";
        String str2 = "B1D23CA45B6A";

        String commonLIS = getLIS(str1.toCharArray(), str2.toCharArray());
        System.out.println("commonLIS = " + commonLIS);
    }

    private static String getLIS(char[] str1, char[] str2) {
        if (str1 == null || str1.length == 0 || str2 == null || str2.length == 0) {
            return "";
        }
        int[][] dp = getDP(str1, str2);
        return buildLIS(str1, str2, dp);
    }

    private static int[][] getDP(char[] str1, char[] str2) {
        // dp[i][j] 表示str1: 0...i 和 str2: 0...j 这两段组成的最大公共子序列长度
        int[][] dp = new int[str1.length][str2.length];

        // Base case
        // dp[0][j]
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], str2[i] == str1[0] ? 1 : 0);
        }

        // dp[i][0]
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }

        // 递归求解dp
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        printDP(dp);

        return dp;
    }

    private static String buildLIS(char[] str1, char[] str2, int[][] dp) {
        char[] res = new char[dp[str1.length - 1][str2.length - 1]];
        int index = res.length - 1;
        int m = dp.length - 1;
        int n = dp[0].length - 1;
        while (index >= 0) {
            // dp[m][n] == dp[m][n-1] 说明 当前值与左方的值相同，当前值不影响LIS结果，将遍历方向向左移动
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                // dp[m][n] == dp[m-1][n] 说明 当前值与上方的值相同，当前值不影响LIS结果，将遍历方向向上移动
                m--;
            } else {
                res[index--] = str1[m];
                m--;
                n--;
            }
        }

        return String.valueOf(res);
    }


    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp[i]));
        }
    }

}
