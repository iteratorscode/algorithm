package book.q8;

/**
 * 换钱的最小货币数，每个货币值只能使用一次
 *
 * @author iterators
 * @since 2021/07/29
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2};
        int aim = 10;

        int result = f(arr, 0, aim);
        System.out.println("result = " + result);
        int dpResult = dp(arr, aim);
        System.out.println("dpResult = " + dpResult);
        int moreSpace = dpWithMoreSpace(arr, aim);
        System.out.println("moreSpace = " + moreSpace);
    }

    /**
     * 暴力递归求解：f(arr, i, aim) 表示使用arr[i...N]种货币，组成aim的最少货币数
     */
    private static int f(int[] arr, int i, int aim) {
        if (i == arr.length || aim == 0) {
            return aim == 0 ? 0 : -1;
        }

        int result = Integer.MAX_VALUE;
        int sub1 = f(arr, i + 1, aim);
        if (sub1 != -1) {
            result = Math.min(result, sub1);
        }

        if (aim - arr[i] >= 0) {
           sub1 =  f(arr, i + 1, aim - arr[i]);
            if (sub1 != -1) {
                result = Math.min(result, sub1 + 1);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }


    /**
     * 动态规划求解：dp[i]组成货币i的最少货币数
     */
    private static int dp(int[] arr, int aim) {
        if (arr == null || aim == 0 || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        // Base case: 只使用arr[0]初始化数组dp
        // Integer.MAX_VALUE 暗示无法组成货币，而非是字面意义的最大值
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i == arr[0] ? 1 : Integer.MAX_VALUE;
        }

        // dp数组迭代: dp[i] = Math.min(dp[i], dp[i-arr[j]] + 1)
        for (int i = 1; i < arr.length; i++) {
            // 使用arr[0...i]构成aim的最小方法数
            for (int j = 1; j < dp.length; j++) {
                int subProblem = Integer.MAX_VALUE;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != Integer.MAX_VALUE) {
                    subProblem = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(subProblem,  dp[j]);
            }
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    /**
     * 动态规划求解：dp[i][j]表示使用数组arr[0...i]元素组成货币j的最少货币数
     */
    private static int dpWithMoreSpace(int[] arr, int aim) {
        int[][] dp = new int[arr.length][aim + 1];

        // Base case
        dp[0][0] = 0;
        // dp[0][....]
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i == arr[0] ? 1 : Integer.MAX_VALUE;
        }

        // dp[...][0]
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = 0;
        }

        // 迭代
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int left = Integer.MAX_VALUE;
                // 如果可以使用当前货币arr[i]来拼成aim=j, 计算此时的最少货币数left
                if (j - arr[i] >= 0) {
                    int subProblem = dp[i][j - arr[i]];
                    if (subProblem != Integer.MAX_VALUE) {
                        subProblem += 1;
                    }
                    left = Math.min(left, subProblem);
                }
                // dp[i][j] = Math.min(dp[i-1][j], dp[i][j-arr[i]])
                dp[i][j] = Math.min(dp[i - 1][j], left);
            }
        }

        return dp[arr.length - 1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1][aim];
    }

}
