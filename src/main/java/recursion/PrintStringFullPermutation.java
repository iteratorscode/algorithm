package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串的子序列
 *
 * @author iterators
 * @since 2021.06.20
 */
public class PrintStringFullPermutation {

    public static void main(String[] args) {
        String str = "aac";
        String result = getFullPermutation(str);
        System.out.println(result);
        String permutationNoRepeat = getFullPermutationNoRepeat(str);
        System.out.println("permutationNoRepeat = " + permutationNoRepeat);
    }

    private static String getFullPermutation(String origin) {
        List<String> result = new ArrayList<>();
        process(result, origin, 0, "");
        return result.toString();
    }

    private static String getFullPermutationNoRepeat(String origin) {
        Set<String> set = new HashSet<>();
        process(set, origin, 0, "");
        return set.toString();
    }

    private static void process(List<String> result, String origin, int index, String path) {
        if (index >= origin.length()) {
            if (!path.isEmpty()) {
                result.add(path);
            }
            return;
        }
        // 获取字符串当前位置值
        char currentIndexContent = origin.toCharArray()[index];
        // 在当前子序列上不添加当前位置值
        process(result, origin, index + 1, path);
        // 在当前子序列上添加当前位置值
        process(result, origin, index + 1, path += currentIndexContent);
    }

    private static void process(Set<String> result, String origin, int index, String path) {
        if (index >= origin.length()) {
            if (!path.isEmpty()) {
                result.add(path);
            }
            return;
        }
        // 获取字符串当前位置值
        char currentIndexContent = origin.toCharArray()[index];
        // 在当前子序列上不添加当前位置值
        process(result, origin, index + 1, path);
        // 在当前子序列上添加当前位置值
        process(result, origin, index + 1, path += currentIndexContent);
    }
}
