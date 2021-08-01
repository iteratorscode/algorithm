package book.q13;

import java.util.Arrays;

/**
 * 最长公共子串
 *
 * @author iterators
 * @since 2021/08/01
 */
public class Solution {

    public static void main(String[] args) {
        String str1 = "1AB2345CD";
        String str2 = "12345EF";
        String result = getLCS(str1.toCharArray(), str2.toCharArray());
        System.out.println("result = " + result);
    }

    private static String getLCS(char[] str1, char[] str2) {
        if (str1.length == 0 || str2.length == 0) {
            return "";
        }
        int[][] dp = getDP(str1, str2);
        return buildLCS(str1, dp);
    }

    private static int[][] getDP(char[] str1, char[] str2) {
        if (str1 == null || str1.length == 0 || str2 == null || str2.length == 0) {
            return new int[0][0];
        }
        // dp[i][j] 表示str1[i] 及 str2[j]构成的最长公共子串的长度，注意str1[i]及str2[j]必须是公共子串的结尾
        int[][] dp = new int[str1.length][str2.length];

        // Base case
        // dp[0][i]
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = str2[i] == str1[0] ? 1 : 0;
        }

        // dp[i][0]
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : 0;
        }

        // 如果str1[i] == str2[j], dp[i][j] = dp[i-1][j-1] + 1
        // 否则 dp[i][j] = 0
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = str1[i] == str2[j] ? dp[i - 1][j - 1] + 1 : 0;
            }
        }

        printDP(dp);

        return dp;
    }


    private static String buildLCS(char[] str1, int[][] dp) {
        int m = dp.length - 1;
        int n = dp[0].length - 1;
        // 找到dp数组的最大值及行索引，行索引是字符串str1中最长公共子串的结束点，起始点等于终点减去长度(dp数组的最大值)
        int maxLength = 0;
        int columnIndex = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] >= maxLength) {
                    maxLength = dp[i][j];
                    columnIndex = i;
                }
            }
        }
        System.out.println("columnIndex = " + columnIndex);
        System.out.println("maxLength = " + maxLength);

        StringBuilder builder = new StringBuilder();
        for (int i = columnIndex - maxLength + 1; i <= columnIndex; i++) {
            builder.append(str1[i]);
        }

        return builder.toString();
    }

    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp[i]));
        }
    }
}
