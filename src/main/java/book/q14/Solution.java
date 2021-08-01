package book.q14;

import java.util.Arrays;

/**
 * 最小编辑代价
 *
 * @author iterators
 * @since 2021/08/01
 */
public class Solution {

    public static void main(String[] args) {
        String str1 = "ad12cd3";
        String str2 = "adcdf";
        // 替换字符串的代价
        int rc = 2;
        // 删除字符的代价
        int dc = 3;
        // 插入字符的代价
        int ic = 5;
        int minCost = getMinCost(str1, str2, ic, dc, rc);
        System.out.println("minCost = " + minCost);
    }

    /**
     * 求将字符串str1变成str2的最小代价
     */
    private static int getMinCost(String str1, String str2, int ic, int dc, int rc) {
        if ((str1 == null && str2 == null) || (str1.length() == 0 && str2.length() == 0)) {
            return 0;
        }

        return process(str1.toCharArray(), str2.toCharArray(), ic, dc, rc);
    }

    private static int process(char[] str1, char[] str2, int ic, int dc, int rc) {

        // dp[i][j]表示将str1[0...i-1]变成str2[0...j-1]的最小代价
        int[][] dp = new int[str1.length + 1][str2.length + 1];

        // Base case
        dp[0][0] = 0;
        // dp[0][i]: 将一个空字符串变成str2，显然需要不停的插入
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i * ic;
        }

        // dp[i][0]: 将str1变成一个空字符串，显然需要不同的删除
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i * dc;
        }

        // dp[i][j]: 来自4中情况的最小值
        // 1. 前一个位置什么也没操作
        // 2. 前一个位置发生了一个替换操作
        // 3. 前一个位置发生了删除操作
        // 4. 前一个位置发生了一个插入操作
        for (int i = 1; i < dp.length; i++) {

            for (int j = 1; j < dp[0].length; j++) {

                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }

                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);

            }
        }
        printDP(dp);
        return dp[str1.length][str2.length];
    }

    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp[i]));
        }
    }
}
