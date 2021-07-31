package book.q9;

/**
 * 换钱的方法数: 每种货币可以使用任意张
 *
 * @author iterators
 * @since 2021/07/30
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = {5, 10, 25, 1};
        // int[] arr = {3, 5};
        int aim = 15;
        int result = f(arr, 0, aim);
        System.out.println("result = " + result);
        int dpResult = dp(arr, aim);
        System.out.println("dpResult = " + dpResult);
    }

    private static int f(int[] arr, int index, int aim) {
        // base case
        if (index == arr.length || aim == 0) {
            return aim == 0 ? 1 : 0;
        }
        // result 表示换钱的方法数
        int result = 0;

        for (int k = 0; k * arr[index] <= aim; k++) {
            result += f(arr, index + 1, aim - k * arr[index]);
        }

        return result;
    }


    /**
     * 动态规划：dp[i]表示使用数组元素arr[0...j]组成目标金额aim=i的方法数
     */
    private static int dp(int[] arr, int aim) {
        int[] dp = new int[aim + 1];

        // Base case: dp[0]
        dp[0] = 1;

        // 使用数组元素arr[0]构成aim的方法数
        // dp的所有元素的默认值为0，意味着组成dp[j]的方法数为0
        for (int i = 1; i <= aim; i++) {
            dp[i] = i % arr[0] == 0 ? 1 : 0;
        }

        // dp[i][j] = (1) + (2)
        // (1): dp[i-1][j] 使用数组元素arr[0...i-1]组成货币j的方法数
        // (2): dp[i][j-arr[i]] 使用数组元素arr[0...i]组成货币j-arr[i]的方法数
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }
}
