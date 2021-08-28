package labuladong.q3;


/**
 * 二分查找
 *
 * @apiNote https://labuladong.gitbook.io/algo/mu-lu-ye/er-fen-cha-zhao-xiang-jie
 * @author iterators
 * @since 2021/08/22
 */
public class Solution {

    public static void main(String[] args) {
        int[] items = {};
        int target = 3;
        int index = binarySearch(items, target);

        int left = 1;

        int right = 10;

        int r = left + (right - left) >>> 1;
        System.out.println("r = " + r);

        int r1 = left + (right - left) / 2;
        System.out.println("r1 = " + r1);
    }

    private static int binarySearch(int[] items, int target){
        return normalBinarySearch(items, target);
    }

    private static int normalBinarySearch(int[] items, int target) {
        int low = 0;
        int high = items.length -1;

        while (low <= high) {
            int mid = low + (high - low) >>> 1;
            if (items[mid] == target) {
                return mid;
            } else if (items[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private static int leftBound(int[] items, int target) {
        int low = 0;
        int high = items.length -1;

        while (low <= high) {
            int mid = low + (high - low) >>> 1;
            if (items[mid] == target) {
                // 向左收缩右边界
                high = mid - 1;
            } else if (items[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // 循环的结束条件为 low == high + 1
        if (low == items.length || items[low] != target) {
            return -1;
        }

        return low;
    }

    private static int rightBound(int[] items, int target) {
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            // int mid = low + (high - low) / 2;
            int mid = low + (high - low) >>> 1; // 为什么这种方式执行超时
            if (items[mid] == target) {
                // 左边界向右收缩
                low = mid + 1;
            } else if (items[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 循环的结束条件为 low == high -1
        if (high < 0 || items[high] != target) {
            return -1;
        }

        return high;
    }
}
