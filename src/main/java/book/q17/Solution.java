package book.q17;

import java.util.Arrays;

/**
 * 龙与地下城游戏问题
 *
 * @author iterators
 * @since 2021/08/08
 */
public class Solution {

    public static void main(String[] args) {
        int[][] map = {{-2, -3, 3}, {-5, -10, 1}, {0, 30, -5}};
        int leftVolume = 1;
        int minBloodVolume = getMinBloodVolume(map, leftVolume);
        System.out.println("minBloodVolume = " + minBloodVolume);
    }

    /**
     * 获取最小血量
     */
    private static int getMinBloodVolume(int[][] map, int leftVolume) {
        int row = map.length;
        int column = map[0].length;

        // dp[i][j]：表示骑士走到位置map[i][j]时所需要的最少血量, 骑士在当前位置向右或者向下走，那么只要满足骑士在当前位置加完血或者扣完血的血量等于dp[i][j+1]或者dp[i+1][j]的值即可
        int[][] dp = new int[row][column];

        // Base case: 右下角往左上角遍历
        dp[row - 1][column - 1] = map[row - 1][column - 1] > 0 ? leftVolume : leftVolume - map[row - 1][column - 1];

        // dp[row-1][j]: 骑士每次只能向右走
        for (int i = column - 2; i >= 0; i--) {
            dp[row - 1][i] = Math.max(dp[row - 1][i + 1] - map[row - 1][i], leftVolume);
        }

        // dp[i][column-1]: 骑士每次只能向下走
        for (int j = row - 2; j >= 0; j--) {
            dp[j][column - 1] = Math.max(dp[j + 1][column - 1] - map[j][column - 1], leftVolume);
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = column - 2; j >= 0; j--) {
                dp[i][j] = Math.min(Math.max(dp[i + 1][j] - map[i][j], leftVolume), Math.max(dp[i][j + 1] - map[i][j], leftVolume));
            }
        }
        printDP(dp);
        return dp[0][0];
    }

    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println("Arrays.toString(dp[i]) = " + Arrays.toString(dp[i]));
        }
    }
}
