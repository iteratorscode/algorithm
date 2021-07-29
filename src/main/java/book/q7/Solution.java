package book.q7;

/**
 * 换钱最少的货币数
 *
 * @author iterators
 * @since 2021/07/28
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = {5, 3, 2};
        int aim = 1;
        int ways = f(arr, 0, aim);
        System.out.println("ways = " + ways);

        int dpWays = dp(arr, aim);
        System.out.println("dpWays = " + dpWays);
    }

    private static int f(int[] arr, int index, int rest) {
        if (arr.length == index) {
            // 不用任何货币数就可以组成0元，返回0
            return rest == 0 ? 0 : -1;
        }

        int ways = Integer.MAX_VALUE;
        for (int k = 0; k * arr[index] <= rest; k++) {
            // k 表示使用的钱币数量
            int subProblem =  f(arr, index + 1, rest - k * arr[index]);
            if (subProblem != -1) {
                int m = subProblem + k;
                ways = Math.min(ways, m);
            }
        }
        return ways == Integer.MAX_VALUE ? -1 : ways;
    }

    private static int dp(int[] arr, int aim) {
        // dp[i][j] 表示只使用任意多张货币arr[0..i]组成aim=j使用的最少货币数
        int[][] dp = new int[arr.length][aim + 1];

        // Base Case1: 初始化dp数组的第一列dp[i][0]
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 0;
        }

        // Base Case2: dp[0][j] 表示只使用arr[0]组成货币j的最小数
        for (int i = 1; i < aim + 1; i++) {
            if (i / arr[0] * arr[0] == i) {
                dp[0][i] = i / arr[0];
            } else {
                dp[0][i] = Integer.MAX_VALUE;
            }
        }
        // 迭代求解 dp[i][j] = Math.min(dp[i-1][j-arr[i] * k] + k), arr[i] * k <= j,k为整数且k>=0
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k * arr[i] <= j; k++) {
                    // 子问题有效，更新dp数组
                    int subProblem = dp[i - 1][j - k * arr[i]];
                    if (subProblem != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(subProblem + k, dp[i][j]);
                    }
                }
            }
        }

        return dp[arr.length - 1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1][aim];
    }
}
