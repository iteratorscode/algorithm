package book.q6;

/**
 * 矩阵的最小路径和
 *
 * @author iterators
 * @since 2021/07/15
 */
public class Solution {

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        int solution = minWays(m);
        System.out.println("solution = " + solution);
    }

    private static int minWays(int[][] m) {
        int x = m.length;
        int y = m[0].length;
        return f(m,x-1, y-1);
    }

    /**
     * f函数返回矩阵中m[i][j]位置的最小路径和
     */
    private static int f(int[][] m, int x, int y) {
        // base case
        if (x == 0 && y == 0) {
            return m[x][y];
        }

        // 最左边数据, 只能是可以往下移动的位置过来的
        if (x == 0) {
            return m[0][y] + f(m, 0, y - 1);
        }

        // 最顶层的数据, 只能是可以往右移动的位置过来的
        if (y == 0) {
            return m[x][0] + f(m, x - 1, 0);
        }
        // 最小路径和是当前位置的m[x][y] + Math.min(到达当前位置左边位置的最小路径和, 达到当前位置的上边位置的最小路径和
        return Math.min(f(m, x-1, y), f(m, x, y-1)) + m[x][y];
    }
}
