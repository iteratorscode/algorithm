package dp;

import java.util.Arrays;

/**
 * 零钱兑换
 * @implNote 数组中给出面额，amount为要兑换到的钱，求兑换出的最少钱币的张数, amount < 0, 不可能凑出
 *
 * @author iterators
 * @since 2021/06/22
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 3, 5};
        int amount = 10;
        int result = coinChange(coins, amount);
        System.out.println("result = " + result);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int change = coinChange(coins, amount, dp);
        System.out.println("change = " + change);
    }

    /**
     * 备忘录解法
     */
    private static int coinChange(int[] coins, int amount, int[] dp) {
        // 初始化base case
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }

        int result = Integer.MAX_VALUE;
        // 穷举所有状态
        for (int coin : coins) {
            // 计算子问题
            int subProblem = coinChange(coins, amount - coin);
            if (subProblem == -1){
                continue;
            }
            // 根据选择做状态转移
            result = Math.min(result, subProblem + 1);
        }
        return result;
    }

    /**
     * 递归解法
     */
    public static int coinChange(int[] coins, int amount) {
        // 初始化base case
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        int result = Integer.MAX_VALUE;
        // 穷举所有状态
        for (int coin : coins) {
            // 计算子问题
            int subProblem = coinChange(coins, amount - coin);
            if (subProblem == -1){
                continue;
            }
            // 根据选择做状态转移
            result = Math.min(result, subProblem + 1);
        }
        return result;
    }
}
