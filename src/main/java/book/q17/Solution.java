package book.q17;

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
        dp[row - 1][column - 1] = map[row - 1][column - 1] > 0 ? leftVolume : 1 - map[row - 1][column - 1];

        // dp[row-1][j]: 骑士每次只能向右走
        for (int i = column - 1; i >= 0; i--) {
            dp[row - 1][i] = Math.max(dp[row - 1][i + 1] - map[row - 1][i], 1);
        }


        return dp[0][0];
    }
}
