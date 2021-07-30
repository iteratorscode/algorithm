package book.q8;

/**
 * 换钱的最小货币数，每个货币值只能使用一次
 *
 * @author iterators
 * @since 2021/07/29
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2};
        int aim = 10;

        int result = f(arr, 0, aim);
        System.out.println("result = " + result);
    }

    private static int f(int[] arr, int i, int aim) {
        if (i == arr.length || aim == 0) {
            return aim == 0 ? 0 : -1;
        }

        int result = Integer.MAX_VALUE;
        int sub1 = f(arr, i + 1, aim);
        if (sub1 != -1) {
            result = Math.min(result, sub1);
        }

        if (aim - arr[i] >= 0) {
           sub1 =  f(arr, i + 1, aim - arr[i]);
            if (sub1 != -1) {
                result = Math.min(result, sub1 + 1);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

}
