package book.q6;

/**
 * 矩阵的最小路径和
 *
 * @author iterators
 * @since 2021/07/15
 */
public class Solution {

    public static void main(String[] args) {
        int[][] m = new int[4][4];

        int solution = minWays(m);
    }

    private static int minWays(int[][] m) {
        int x = m.length;
        int y = m[0].length;
        return f(x-1, y-1);
    }

    /**
     * f函数返回矩阵中m[i][j]位置的最小路径和
     */
    private static int f(int i, int j) {
        // base case

        //
        return 0;
    }
}
