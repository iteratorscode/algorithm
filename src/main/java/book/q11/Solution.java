package book.q11;

/**
 * 汉诺塔问题
 *
 * @author iterators
 * @since 2021/07/31
 */
public class Solution {

    public static void main(String[] args) {
        int n = 2;
        func(n, "left", "mid", "right");
    }

    private static void func(int n, String left, String mid, String right) {
        if (n == 1) {
            System.out.printf("Move from %s to %s%n", left, right);
        } else {
            // 将 N-1 从左边移动到中间
            func(n - 1, left, right, mid);
            // 将左边剩余的1个盘子移动到右边
            func(1, left, mid, right);
            // 将中间剩余的n-1个盘子移动到右边
            func(n - 1, mid, left, right);
        }
    }
}
