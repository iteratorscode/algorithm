package book.q15;

import java.util.Arrays;

/**
 * 最小编辑距离
 *
 * @author iterators
 * @since 2021/08/01
 */
public class Solution {

    public static void main(String[] args) {
        String str1 = "horse";
        String str2 = "ros";

        int minEditDistance = getMinEditDistance(str1, str2);
        System.out.println("minEditDistance = " + minEditDistance);
    }

    private static int getMinEditDistance(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        return process(str1.toCharArray(), str2.toCharArray());
    }

    private static int process(char[] str1, char[] str2) {
        // dp[i][j] 表示将str1[0...i-1]变成str2[0...j-1]的最小编辑距离，因为dp[0]位置是空的表示
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        // base case
        // 两个空字符串，不用任何操作即是相同的
        dp[0][0] = 0;
        // dp[0][i]: 将一个空字符串变成str2的最小编辑距离，显然只能不断的执行插入操作
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        // dp[i][0]: 将str1变成空字符串，显然只能不断的删除
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }

        // dp[i][j]: 来自四种情况
        // 1: 从上一个情况什么也不操作
        // 2 从上一个情况发生了替换操作
        // 3 上一个情况发生了插入操作
        // 4 上一个情况发生了删除操作
        // dp[i][j]表示将str1[0...i-1]转换成str2[0...j-1]的最小距离，那么dp[i][j]可能来自4种情况
        // str1[0...i-2] 与 str2[0...j-2] 经过变化能够成为 str1[0...i-1] 与str2[0...j-1], 即由dp[i-1][j-1]演化到dp[i][j]
        // 1: 如果str1[0...i-2] == str2[0...j-2]，当str1[i-1]==str2[j-1], 那么意味着什么也不用做就可以，dp[i][j] = dp[i-1][j-1]
        // 2: 如果str1[0...i-2] == str2[0...j-2]，当str1[i-1]!=str2[j-1], 意味着将str1[i-1]替换成str2[j-1]，dp[i][j] = dp[i-1][j-1] + 1
        // 3: 如果str1[0...i-1] == str2[0...j-2]，此时要使两者相同，需要在str1种插入一个字符str2[j-1], dp[i][j] = dp[i][j-1] + 1
        // 4: 如果str1[0...i-2] == str2[0...j-1]，此时要使两者相同，需要将str1[i-1]处的字符删除，dp[i][j] = dp[i-1][j] + 1
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 替换
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 删除
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                // 插入
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
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
