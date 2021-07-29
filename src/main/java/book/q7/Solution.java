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
        int aim = 20;
        int ways = f(arr, 0, aim);
        System.out.println("ways = " + ways);

        int dpWays = dp(arr, aim);
        System.out.println("dpWays = " + dpWays);

        int result = dpWithSmallSpace(arr, aim);
        System.out.println("result = " + result);
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


    private static int dpWithSmallSpace(int[] arr, int aim) {
        // 压缩数组
        // dp[i][j]的更新只依赖上一跳dp[i-1][0...N]的结果，所以可以将二维数组进行压缩
        // dp[i]表示组成目标i的最少货币数
        int[] dp = new int[aim + 1];
        
        // Base Case: dp[0] 表示
        // 使用arr[0]填充的dp数组是使用arr[0..1]填充数组dp的上一跳，上一跳的信息必须都初始化
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            if (i - arr[0] >= 0 && dp[i - arr[0]] != Integer.MAX_VALUE) {
                dp[i] = dp[i - arr[0]] + 1;
            }
        }
        
        // 迭代求解dp[i] = Math.min(dp[i-k*arr[i]]) + k
        for (int i = 1; i < arr.length; i++) {
            // 使用货币arr[0...i]构成aim的最少货币数
            for (int j = 1; j < dp.length; j++) {
                int left = Integer.MAX_VALUE;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != Integer.MAX_VALUE) {
                    left = Math.min(left, dp[j - arr[i]] + 1);
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }

        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }
}
