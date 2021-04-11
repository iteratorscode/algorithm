package leetcode.q7;

/**
 * Reverse Integer
 *
 * @author iterators
 * @date 2021.04.11
 */
public class Solution {

    public static int reverse(int x) {
        int result = 0;
        while (x != 0){
            int remain = x - x / 10 * 10;
            int newResult = result * 10 + remain;
            if ((newResult - remain) / 10 != result) {
                return 0;
            }
            result = newResult;
            x=x/10;
        }
        return result;
    }

    public static void main(String[] args) {
        // 1534236469 导致整型溢出
        // 964632435 * 10 > Integer.MAX_VALUE
        // 9646324351 > Integer.MAX_VALUE
        System.out.println("reverse(1534236469) = " + reverse(1534236469));
    }
}
