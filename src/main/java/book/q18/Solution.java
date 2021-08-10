package book.q18;

/**
 * 数字字符串转换为字母组合的种数
 *
 * @implNote https://www.bilibili.com/video/BV11f4y1677m?p=1
 * @author iterators
 * @since 2021/08/09
 */
public class Solution {

    public static void main(String[] args) {
        String str = "1111";
        int ways = f(str.toCharArray(), 0);
        System.out.println("ways = " + ways);
    }

    private static int f(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }

        if (str[i] == '0') {
            return 0;
        }

        int result = f(str, i + 1);
        if (i + 1 < str.length &&
                (Integer.parseInt(str[i] + "") * 10 + Integer.parseInt(str[i + 1] + "")) < 27) {
            result += f(str, i + 2);
        }
        return result;
    }
}
