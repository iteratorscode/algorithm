package dp;

/**
 * 背包问题
 *
 * @author zhanghui
 * @date 2021/7/13 下午7:30
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5};
        int[] values = {2, 4, 4, 5, 6};
        int bag = 7;

        // 递归解法
        int maxValue = solveKnapsackByRecursion(weights, values, 0, bag);
        System.out.println("maxValue = " + maxValue);
        int maxValueDp = solveKnapsackByDp(weights, values, bag);
        System.out.println("maxValueDp = " + maxValueDp);
    }

    /**
     * @return 表示背包剩余体积为 restVolume 时，从index位置开始装货物能够获取的最大价值
     */
    private static int solveKnapsackByRecursion(int[] weights, int[] values, int index, int restVolume) {
        // 剩余体积小于0, 表示无效
        if (restVolume < 0) {
            return -1;
        }
        // 当前位置已经超出数组，价值为0
        if (index == weights.length) {
            return 0;
        }
        // 不装当前货物能够获取的最大价值
        int v1 = solveKnapsackByRecursion(weights, values, index + 1, restVolume);
        // 装当前获取能够获取的最大价值
        int v2 = -1;
        int v2Next = solveKnapsackByRecursion(weights, values, index + 1, restVolume - weights[index]);
        if (v2Next != -1) {
            v2 = v2Next + values[index];
        }
        return Math.max(v1, v2);
    }


    /**
     * 使用dp求解
     *
     * @return dp[i][j]表示将从i开始的货物装入体积为j的包裹中能够获取的最大价值
     */
    private static int solveKnapsackByDp(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length + 1][bag + 1];
        // base case
        // bag = 0, 所有获取价值为0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        // index = weights.length
        for (int i = 0; i < dp[0].length; i++) {
            dp[weights.length][i] = 0;
        }

        for (int i = weights.length - 1; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                // 不装
                dp[i][j] = dp[i + 1][j];
                if (j - weights[i] >= 0) {
                    // 装
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[0][bag];
    }
}
