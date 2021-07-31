package book.q10;

import java.util.Arrays;

/**
 * 数组最长递增子数列
 *
 * @author iterators
 * @since 2021/07/31
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        int maxSequenceLength = getMaxSequenceLength(arr);
        System.out.println("maxSequenceLength = " + maxSequenceLength);
        String lis = geneteLIS(arr);
        System.out.println("lis = " + lis);
    }

    private static int getMaxSequenceLength(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        // dp[i]表示以arr[i]结尾的最长递增子序列的长度
        int[] dp = new int[arr.length];
        // base case: 以元素arr[0]结尾的最长递增子序列的长度为1
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            // 如果最长递增子序列以 arr[i]结尾，那么最长递增子序列的长度为以dp[0...i-1]中的最大值加1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println("dp = " + Arrays.toString(dp));
        return max;
    }


    /**
     * 生成数组的最长递增子序列
     */
    private static String geneteLIS(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return Arrays.toString(arr);
        }

        // 生成dp数组
        int[] dp = geneteDP(arr);
        // 根据dp数组构造最长递增子序列
        return buildLIS(arr, dp);
    }

    private static int[] geneteDP(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            int[] ints = new int[1];
            ints[0] = 1;
            return ints;
        }

        int[] dp = new int[arr.length];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        return dp;
    }


    private static String buildLIS(int[] arr, int[] dp) {
        // 1 找到最大长度的值和索引位置
        int max = 1;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= max) {
                index = i;
                max = dp[i];
            }
        }

        // 遍历dp数组，构造出最长递增子序列
        // 最长递增子序列长度为输出数组的长度
        int[] lis = new int[max];
        lis[--max] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (dp[i] + 1 == dp[index] && arr[i] < arr[index]) {
                lis[--max] = arr[i];
                index = i;
            }
        }

        return Arrays.toString(lis);
    }
}
