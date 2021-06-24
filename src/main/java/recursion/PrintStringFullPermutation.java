package recursion;

import java.util.*;

/**
 * 字符串的子序列
 *
 * @author iterators
 * @since 2021.06.20
 */
public class PrintStringFullPermutation {

    public static void main(String[] args) {
        String str = "aac";
        String subset = getSubset(str);
        System.out.println(subset);
        String subsetNoRepeat = getSubsetNoRepeat(str);
        System.out.println("subsetNoRepeat = " + subsetNoRepeat);

        String str2 = "abc";
        System.out.println("getFullPermutation(str2) = " + getFullPermutation(str2));
    }

    /**
     * 打印全部子集
     */
    private static String getSubset(String origin) {
        List<String> result = new ArrayList<>();
        process(result, origin, 0, "");
        return result.toString();
    }

    private static String getSubsetNoRepeat(String origin) {
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

    /**
     * 获取字符串的全排列
     */
    public static String getFullPermutation(String str) {
        char[] chars = str.toCharArray();
        List<List<String>> result = new LinkedList<>();
        LinkedList<String> path = new LinkedList<>();
        process(result, chars, path);
        return result.toString();
    }

    private static void process(List<List<String>> result, char[] chars, LinkedList<String> path) {
        //  满足条件，加入到候选集合中
        if (path.size() == chars.length) {
            result.add(new LinkedList<>(path));
            return;
        }

        for (char c : chars) {
            // 过滤
            if (path.contains(c + "")) {
                continue;
            }
            path.add(c + "");
            process(result, chars, path);
            path.removeLast();
        }

    }


}
