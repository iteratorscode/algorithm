package labuladong.q1;

import java.util.Arrays;

/**
 * 零钱兑换问题（每个货币无限个）
 *
 * @author iterators
 * @since 2021/08/11
 */
public class Solution {

    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 3;
        int minCoinNumbers = getMinCoinNumbers(coins, amount);
        System.out.println("minCoinNumbers = " + minCoinNumbers);
    }

    public static int getMinCoinNumbers(int[] coins, int amount) {
        if (coins == null || amount == 0) {
            return 0;
        }
        // dp[i][j]: 表示使用前coins[0...i]个货币构成金额j的最小货币数量
        int[][] dp = new int[coins.length][amount + 1];

        // base case
        // dp[i][0]: 使用货币coins[0...i]构成金额0的最小货币数
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 0;
        }

        // dp[0][j]: 表示只使用coins[0]构成货币j的最小数量
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i % coins[0] == 0 ? i / coins[0] : Integer.MAX_VALUE;
        }

        // 状态转移
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k * coins[i] <= j; k++) {
                    int left = dp[i - 1][j - k * coins[i]];
                    if (left != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], left + k);
                    }
                }
            }
        }

        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }
}
