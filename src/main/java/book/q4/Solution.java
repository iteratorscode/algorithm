package book.q4;

/**
 * 最大连续1的个数
 *
 * @author zhanghui
 * @date 2021/2/23 下午5:52
 */
public class Solution {

    public static int getMaxCount(int[] arr) {
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {
            // 从=1的位置开始计数
            if (arr[i] == 1) {
                int j = i + 1;
                while (j < arr.length && arr[j] == 1) {
                    j++;
                }
                maxCount = Math.max(maxCount, j - i);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 1, 1, 1, 1};
        System.out.println("getMaxCount(arr) = " + getMaxCount(arr));
    }
}