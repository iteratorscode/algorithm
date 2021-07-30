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
        int aim = 2;
        int result = f(arr, 0, aim);
        System.out.println("result = " + result);
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
}
