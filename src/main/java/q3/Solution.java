package q3;

import java.util.HashSet;
import java.util.Set;

/**
 * 求数组中字符串类型
 *
 * @author iterators
 * @date 2021.02.17
 */
public class Solution {

    public static Integer s1(String[] arr) {
        // 1 对字符串生成一个类型签名，表示这个类型
        // 2 所有类型记录在Set集合中
        Set<Object> results = new HashSet<>();
        for (String str : arr) {
            // Object sign = buildSign(str);
            Object sign = buildSign2(str);
            results.add(sign);
        }
        return results.size();
    }

    /**
     * 签名算法：对字符串中所有出现过的字母按照字母表顺序从小到大组成一个签名字符串（多次出现的字母只计算一个）
     */
    private static String buildSign(String str) {
        boolean[] characterAppears = new boolean[26];
        for (char character : str.toCharArray()) {
            characterAppears[character - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < characterAppears.length; i++) {
            if (characterAppears[i]) {
                sb.append(i + 'a');
            }
        }
        return sb.toString();
    }

    /**
     * 签名算法：将字符串映射成一个整数
     * 对于32位整数，如果某个字母出现过就将对应二进制位设置成1，否则设置成0
     * 利用位运算降低空间复杂度
     */
    private static Integer buildSign2(String str) {
        int sign = 0;
        for (char c : str.toCharArray()) {
            // 计算左移位数
            // 使用或运算将字母出现的对应位置置1
            sign |= 1 << (c - 'a');
        }
        return sign;
    }

}
