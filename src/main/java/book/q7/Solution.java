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
}
