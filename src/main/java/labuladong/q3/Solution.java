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
}
