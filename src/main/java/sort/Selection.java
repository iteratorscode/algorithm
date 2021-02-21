package sort;

/**
 * 选择排序
 *
 * @author iterators
 * @date 2021.02.21
 */
public class Selection {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 -> N位置，选择最小元素放在0位置
        // 1 -> N位置，选择第二小元素放在1位置
        // 2 -> N位置，选择第三小元素放在1位置
        // 。。。每次选择剩余最小元素放在指定位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[i] ? j : minIndex;
            }
            swapElement(arr, i, minIndex);
        }
    }

    private static void swapElement(int[] arr, int i, int minIndex) {
        int tmp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = tmp;
    }
}
